package com.example.birthday.controller;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/lucky")
public class ChatbotController {

    // OpenAiChatModel ではなく汎用的な ChatModel を使用するのが推奨
    private final ChatModel chatModel;

    // コンストラクタ注入（確実な依存性の注入）
    public ChatbotController(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/chatbot")
    public String showChatbot(HttpSession session, Model model) {
        // セッションから会話履歴を取得し、画面に渡す
        model.addAttribute("chatHistory", session.getAttribute("chatHistory"));
        return "chatbot";
    }

    @PostMapping("/chat")
    public String chat(@RequestParam String message, HttpSession session, Model model) {
        // 1. セッションから履歴を取得（なければ新規作成）
        @SuppressWarnings("unchecked")
        List<Map<String, String>> history = (List<Map<String, String>>) session.getAttribute("chatHistory");
        if (history == null) {
            history = new ArrayList<>();
        }

        // 2. ユーザーの発言を履歴に追加
        history.add(Map.of("role", "user", "content", message));

        try {
            // 3. AIへの命令（システムプロンプトを定義）
            String systemPrompt = "あなたは優秀な占い師です。親身になって、これからの道しるべとなるアドバイスを優しく、少し神秘的なトーンで回答してください。";
            
            // 4. API呼び出し（gpt-4o を指定）
            var options = OpenAiChatOptions.builder()
                    .withModel("gpt-4o")
                    .build();
            
            // プロンプトを構築して呼び出し
            String aiResponse = chatModel.call(new Prompt(systemPrompt + "\n相談内容：" + message, options))
                                        .getResult().getOutput().getContent();

            // 5. AIの回答を履歴に追加
            history.add(Map.of("role", "assistant", "content", aiResponse));

        } catch (Exception e) {
            // エラー時：アプリを落とさず、占い師風のエラーメッセージを返す
            history.add(Map.of("role", "assistant", "content", "今は少し星の巡りが悪いようです。また後ほどお話ししましょう。"));
            e.printStackTrace(); // 開発者用のログ確認
        }

        // 6. セッションを更新して画面へ渡す
        session.setAttribute("chatHistory", history);
        model.addAttribute("chatHistory", history);
        
        return "chatbot";
    }
}
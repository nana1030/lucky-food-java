package com.example.birthday.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.ai.openai.OpenAiChatModel; // 追加
import org.springframework.beans.factory.annotation.Autowired; // 追加
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/lucky")
public class ChatbotController {

    // Spring AIが application.properties の設定（APIキーやモデル名）を自動で読み込んでくれます
    @Autowired
    private OpenAiChatModel chatModel;

    @GetMapping("/chatbot")
    public String showChatbot(Model model) {
        return "chatbot";
    }

    @PostMapping("/chat")
    public String chat(@RequestParam String message, Model model) {
        // 1. 本物のChatGPT（gpt-4o）にユーザーのメッセージを送信して、返答を受け取る
        // 🔮 占い師っぽく答えてもらうために、プロンプトを少し工夫します
        String systemPrompt = "あなたは優秀な占い師です。親身になって、これからの道しるべとなるアドバイスを優しく、少し神秘的なトーンで回答してください。\n相談内容：";
        String aiResponse = chatModel.call(systemPrompt + message);

        // 2. 画面のThymeleaf（th:each）の順番に合わせて履歴を作成
        List<Map<String, String>> history = new ArrayList<>();
        
        // ユーザーの入力を先に（画面の上側）
        history.add(Map.of("role", "user", "content", message));
        
        // 占い師（AI）の返答を後に（画面の下側）
        history.add(Map.of("role", "assistant", "content", aiResponse));

        // 3. 画面にデータを渡す
        model.addAttribute("chatHistory", history);
        
        return "chatbot";
    }
}
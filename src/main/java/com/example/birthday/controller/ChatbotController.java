package com.example.birthday.controller;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
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

    private final ChatModel chatModel;

    public ChatbotController(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/chat")
    public String showChat(HttpSession session, Model model) {
        model.addAttribute("chatHistory", session.getAttribute("chatHistory"));
        return "chatbot";
    }

    @PostMapping("/chat")
    public String chat(@RequestParam String message, HttpSession session, Model model) {
        @SuppressWarnings("unchecked")
        List<Map<String, String>> history = (List<Map<String, String>>) session.getAttribute("chatHistory");
        if (history == null) history = new ArrayList<>();

        history.add(Map.of("role", "user", "content", message));

        try {
            // プロンプトを実行（モデル名などは application.properties が適用されます）
            String response = chatModel.call(message);
            history.add(Map.of("role", "assistant", "content", response));
        } catch (Exception e) {
            // 認証エラー等が発生しても、アプリは落とさずメッセージを表示
            history.add(Map.of("role", "assistant", "content", "すみません、星の配置が読めませんでした。もう一度試してみてください。"));
            e.printStackTrace(); // ログには詳細が出る
        }

        session.setAttribute("chatHistory", history);
        model.addAttribute("chatHistory", history);
        return "chatbot";
    }
}
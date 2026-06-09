package com.example.birthday.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GourmetController {

    // 💡 無料で取得したホットペッパーのAPIキーをここに貼り付けます
    private final String API_KEY = "6f40395c3fb8a470";

    @GetMapping("/api/gourmet")
    public String getGourmet(@RequestParam double lat, @RequestParam double lng) {
        
        // ホットペッパーAPIのURLを構築
        String url = "https://webservice.recruit.co.jp/hotpepper/gourmet/v1/"
                + "?key=" + API_KEY
                + "&lat=" + lat
                + "&lng=" + lng
                + "&range=3"
                + "&count=5"
                + "&format=json";

        // 外部のホットペッパーAPIにリクエストを送り、結果をそのまま文字列（JSON）として受け取る
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        // フロント（food.html）側にデータをそのまま返却する
        return result;
    }
}
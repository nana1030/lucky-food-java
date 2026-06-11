package com.example.birthday.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GourmetController {

    private final String API_KEY = "6f40395c3fb8a470";

    @GetMapping("/api/gourmet")
    public String getGourmet(
            @RequestParam double lat, 
            @RequestParam double lng,
            @RequestParam(required = false) String element) {

        String keyword = "";
        String genre = ""; // ジャンルコード用変数

        if (element != null) {
        	
            switch (element) {
            
	            case "木" -> { 
	                keyword = "パスタ,サンドイッチ,サラダ,ピザ,そば,冷やし中華,生春巻き,アボカド,キッシュ,スイーツ,ハンバーグ,お茶漬け,カレー,枝豆,キウイ,グリーンカレー,青椒肉絲,よもぎ";             // キーワードは絞り込みすぎない単語を1つ
	                genre = "G006,G014,G004,G007,G009,G005";          // イタリアン, 軽食 , 和食 ,アジア・エスニック,ハンバーグ, 
	            }
	            case "火" -> { 
	                keyword = "焼肉,麻婆豆腐,キムチ,ラーメン,カレー,ダッカルビ,ナポリタン,ハンバーグ,ミネストローネ,ビーフシチュー,焼き鳥,タコス,担々麺,ホットサンド,火鍋,パイ,トマト,赤ワイン,チーズケーキ"; 
	                genre = "G008,G007,G009,G006,G005,G014,G003,";  // 焼肉,中華,イタリアン,洋食,軽食,
	            }
	            case "土" -> { 
	                keyword = "カレー,肉じゃが,オムライス,グラタン,さつまいも,ホットケーキ,ハニートースト,プリンアラモード,バナナケーキ,豚汁,栗ご飯,南瓜"; 
	                genre = "G009,G004,G005,G014,";          // カレー,和食,洋食,軽食,
	            }
	            case "金" -> { 
	                keyword = "豆乳鍋,しゃぶしゃぶ,ラーメン,ムニエル,天ぷら,カルボナーラ,チーズフォンデュ,小籠包,飲茶,豚しゃぶ,棒棒鶏,おでん,参鶏湯,コロッケ,杏仁豆腐,チーズリゾット,塩タン,白湯ラーメン,豆腐,雑炊"; 
	                genre = "G004,G007,G005,G006,G002,G014,G008";          // 和食,中華,洋食,イタリアン,ダイニングバー,軽食,焼肉・ホルモン
	            }
	            case "水" -> { 
	                keyword = "海鮮丼,寿司,ラーメン,うどん,しじみ,サラダ,牡蠣,サバ,ひじき,いか,わかめ,お茶漬け,湯豆腐,刺身,ちゃんこ,クラムチャウダー,ブルーベリー"; 
	                genre = "G004,G007,G006,G005,G003,G014";          // 和食,中華,イタリアン・フレンチ,洋食,創作料理,軽食
	            }
            }
        }

        String url = "https://webservice.recruit.co.jp/hotpepper/gourmet/v1/"
                + "?key=" + API_KEY
                + "&lat=" + lat
                + "&lng=" + lng
                + "&range=5"
                + "&count=30"
                + "&format=json";

        // ジャンルコードがあれば優先的に追加
        if (!genre.isEmpty()) {
            url += "&genre=" + genre;
        }
        // ヒットしない場合はキーワードを外すか、ジャンルのみにするのがコツ
        
        System.out.println("★アクセスURL: " + url); // これを必ずコンソールで確認

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}
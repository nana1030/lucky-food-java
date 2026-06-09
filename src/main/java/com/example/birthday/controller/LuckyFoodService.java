package com.example.birthday.controller;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class LuckyFoodService {

    public LuckyFoodFortune pickRandomFood(String userElement) {
    	
        // 🌟 毎回違うランダムな数字を作るために、Randomオブジェクトを使用します
        Random random = new Random();

        // 隙間のない完璧な20枚の画像リスト
        List<String> mokuImages   = List.of("moku_01.png","moku_02.png","moku_03.png","moku_04.png","moku_05.png","moku_06.png","moku_07.png","moku_08.png","moku_09.png","moku_10.png","moku_11.png","moku_12.png","moku_13.png","moku_14.png","moku_15.png","moku_16.png","moku_17.png","moku_18.png","moku_19.png","moku_20.png");
        List<String> mizuImages   = List.of("mizu_01.png","mizu_02.png","mizu_03.png","mizu_04.png","mizu_05.png","mizu_06.png","mizu_07.png","mizu_08.png","mizu_09.png","mizu_10.png","mizu_11.png","mizu_12.png","mizu_13.png","mizu_14.png","mizu_15.png","mizu_16.png","mizu_17.png","mizu_18.png","mizu_19.png","mizu_20.png");
        List<String> hiImages     = List.of("hi_01.png","hi_02.png","hi_03.png","hi_04.png","hi_05.png","hi_06.png","hi_07.png","hi_08.png","hi_09.png","hi_10.png","hi_11.png","hi_12.png","hi_13.png","hi_14.png","hi_15.png","hi_16.png","hi_17.png","hi_18.png","hi_19.png","hi_20.png");
        List<String> kinImages    = List.of("kin_01.png","kin_02.png","kin_03.png","kin_04.png","kin_05.png","kin_06.png","kin_07.png","kin_08.png","kin_09.png","kin_10.png","kin_11.png","kin_12.png","kin_13.png","kin_14.png","kin_15.png","kin_16.png","kin_17.png","kin_18.png","kin_19.png","kin_20.png");
        List<String> tsuchiImages = List.of("tsuchi_01.png","tsuchi_02.png","tsuchi_03.png","tsuchi_04.png","tsuchi_05.png","tsuchi_06.png","tsuchi_07.png","tsuchi_08.png","tsuchi_09.png","tsuchi_10.png","tsuchi_11.png","tsuchi_12.png","tsuchi_13.png","tsuchi_14.png","tsuchi_15.png","tsuchi_16.png","tsuchi_17.png","tsuchi_18.png","tsuchi_19.png","tsuchi_20.png");

        String luckyElement = "";
        String chosenImage = "";

        // 🔮 五行の判定ロジック（random.nextInt を使って、毎回完全にバラバラの抽選を行います！）
        if ("木".equals(userElement)) {
            luckyElement = "水";
            chosenImage = mizuImages.get(random.nextInt(mizuImages.size())); 
            
        } else if ("水".equals(userElement)) {
            luckyElement = "金";
            chosenImage = kinImages.get(random.nextInt(kinImages.size()));
            
        } else if ("金".equals(userElement)) {
            luckyElement = "土";
            chosenImage = tsuchiImages.get(random.nextInt(tsuchiImages.size()));
            
        } else if ("土".equals(userElement)) {
            luckyElement = "火";
            chosenImage = hiImages.get(random.nextInt(hiImages.size()));
            
        } else if ("火".equals(userElement)) {
            luckyElement = "木";
            chosenImage = mokuImages.get(random.nextInt(mokuImages.size()));
            
        } else {
            luckyElement = "水";
            chosenImage = mizuImages.get(random.nextInt(mizuImages.size()));
        }

        return new LuckyFoodFortune(luckyElement, chosenImage);
    }
}
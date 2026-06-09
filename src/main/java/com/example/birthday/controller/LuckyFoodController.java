package com.example.birthday.controller;

import java.util.List; // 🌟 Listを使うために追加

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LuckyFoodController {

    @Autowired
    private LuckyFoodService luckyFoodService;

    @GetMapping("/lucky-food") 
    public String showLuckyFood(
            @RequestParam(value = "element", required = false, defaultValue = "木") String userElement, 
            Model model) {
        
        // 🌟 ① サービスを3回呼び出して、それぞれ違うランダムな結果（fortune）を作ります
        LuckyFoodFortune fortune1 = luckyFoodService.pickRandomFood(userElement);
        LuckyFoodFortune fortune2 = luckyFoodService.pickRandomFood(userElement);
        LuckyFoodFortune fortune3 = luckyFoodService.pickRandomFood(userElement);

        // 🌟 ② 3つの結果を1つのリスト（List）にギュッとまとめます
        List<LuckyFoodFortune> fortuneList = List.of(fortune1, fortune2, fortune3);

        // 🌟 ③ 画面（HTML）に、3つまとまったリストを引き渡します
        // HTML側では「fortuneList」という名前でこの3つのデータを扱えるようになります
        model.addAttribute("fortuneList", fortuneList);
        
        // 🌟 ④ 属性名（「水」など）を画面のタイトル等で一箇所だけ使いたい時のために、代表して1つ目を送っておきます
        model.addAttribute("elementName", fortune1.getElement());

        // 表示するHTMLファイル名
        return "lucky_food"; 
    }
}
package com.example.birthday.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoonController {

    @GetMapping("/api/moon")
    public String getMoonPhase() {
        // 本来は外部の複雑な天文APIを叩きますが、海外のAPIは止まりやすいため、
        // 確実に動くようJava側で簡易天体計算（満ち欠けの周期29.53日）を行って正確な現在の月齢を算出します。
        // 基準となる新月の日（2023年1月22日など）からの経過日数で計算します。
        
        LocalDate baseNewMoon = LocalDate.of(2023, 1, 22);
        LocalDate today = LocalDate.now();
        
        long daysBetween = ChronoUnit.DAYS.between(baseNewMoon, today);
        double moonAge = daysBetween % 29.53059; // 月の満ち欠け周期で割った余り
        
        // 四捨五入してフロントに渡しやすいJSON形式の文字列を作る
        double roundedAge = Math.round(moonAge * 10.0) / 10.0;
        
        return "{\"phase\": " + roundedAge + "}";
    }
}
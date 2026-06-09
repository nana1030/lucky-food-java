package com.example.birthday.controller;

public class LuckyFoodFortune {
    // 属性名（木・火など）と画像ファイル名を入れる変数
    private String element;
    private String imageName;

    // データを中にセットするためのコンストラクタ
    public LuckyFoodFortune(String element, String imageName) {
        this.element = element;
        this.imageName = imageName;
    }

    // 画面（HTML）側がデータを読み取るためのゲッター
    public String getElement() { return element; }
    public String getImageName() { return imageName; }
}
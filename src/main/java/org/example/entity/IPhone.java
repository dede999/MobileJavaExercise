package org.example.entity;

import lombok.Getter;

@Getter
public class IPhone {
    private final String model;
    private final String color;
    private final int price;
    private final MusicPlayer musicPlayer = new MusicPlayer();
    private final PhoneCentral phone = new PhoneCentral();
    private final WebBrowser safari = new WebBrowser();

    public IPhone(String model, String color, int price) {
        this.model = model;
        this.color = color;
        this.price = price;
    }
}

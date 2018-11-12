package com.example.kittenapp.model;

import android.content.Context;
import android.content.res.Resources;

import com.example.kittenapp.R;
import com.example.kittenapp.activity.MainActivity;

public class Kitten {
    private static final String[] names = {"Оливер", "Джаспер", "Смоки", "Гизмо", "Чарли", "Джек", "Макс", "Роки", "Оскар",
            "Перец", "Бейли", "Лаки", "Саймон", "Джордж", "Феликс", "Бандит", "Декстер", "Ромео", "Каспер", "Блэки",
            "Честер", "Фрэнки", "Мерфи", "Бэтман", "Клео"};
    private static final String[] adjectives = {"Мартовский", "Соседский", "Полосатый", "Облезлый", "Кошачий", "Сытый",
            "Пушистый", "Мышиный", "Древесный", "Бродячий", "Черно-белый", "Рыжий", "Дымчатый", "Говорящий",
            "Тигровый", "Бездомный", "Породистый", "Хозяйский", "Ленивый", "Персидский"};
    private static int kittenAmount = 0;
    private String name;
    private String number;
    private String phone;
    private String imageUrl;

    public Kitten(KittenJson kittenJson) {
        Context context = MainActivity.getContext();

        this.name = context.getString(R.string.kitten_name_template) + " " + generateName();
        this.number = context.getString(R.string.kitten_num_template) + (kittenAmount + 1);


        this.phone = generatePhone();
        this.imageUrl = kittenJson.getUrl();
        kittenAmount++;
    }

    private String generateName() {
        int adjNumber = (int) (Math.random() * adjectives.length);
        int nameNumber = (int) (Math.random() * names.length);
        String name = (int) (Math.random() * 2) % 2 == 0 ? "" : adjectives[adjNumber] + " ";//1 of 2 kittens get 2 adjectives
        adjNumber = (int) (Math.random() * adjectives.length);
        return name + adjectives[adjNumber] + " " + names[nameNumber];
    }

    private String generatePhone() {
        int num = (int) (1000000 + Math.random() * 9999999);
        return "097" + num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

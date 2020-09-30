package com.company;

public class Player {

    public String name;
    public static int money = 1000;

    public Player(String name){
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }
}

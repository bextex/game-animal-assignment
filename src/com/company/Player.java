package com.company;

import java.util.ArrayList;

public class Player {

    public static ArrayList<Player> players = new ArrayList<>();
    ArrayList<Animal> animals = new ArrayList<>();
    ArrayList<Food> foods =  new ArrayList<>();

    public String name;
    public int money = 1000;

    public Player(String name){
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }
}

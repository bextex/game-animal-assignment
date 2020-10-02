package com.company;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Player {

    public static ArrayList<Player> players = new ArrayList<>();
    ArrayList<Animal> animals = new ArrayList<>();
    LinkedHashMap<Food, Integer> foods = new LinkedHashMap<>();

    public String name;
    public int money = 1000;

    public Player(String name){
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public void addFood(Food food, int foodInKg) {
        if(!foods.containsKey(food)){
            foods.put(food, foodInKg);
        } else {
            int value = foods.get(food);
            int newValue = value + foodInKg;
            foods.put(food, newValue);
        }
        for(Food key : foods.keySet()){
            System.out.println(key + ": " + foods.get(key));
        }
    }

    public void removeFood(Food food, int foodInKg){
        if(!foods.containsKey(food)){
            System.out.println("Cannot feed with food you don't have.");
        } else {
            int value = foods.get(food);
            if(value < foodInKg){
                System.out.println("You cannot feed with " + foodInKg + " you only have " + value + " kg's of food.");
            } else {
                int newValue = value - foodInKg;
                foods.put(food, newValue);
            }
        }
        for(Food key : foods.keySet()){
            System.out.println(key + ": " + foods.get(key));
        }
    }
}

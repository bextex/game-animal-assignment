package com.company;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Player {

    public static ArrayList<Player> players = new ArrayList<>();
    ArrayList<Animal> animals = new ArrayList<>();
    LinkedHashMap<String, Integer> foods = new LinkedHashMap<>();

    public String name;
    public int money = 1000;

    public Player(String name){
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public void addFood(String choice, int foodInKg) {
        if(!foods.containsKey(choice)){
            foods.put(choice, foodInKg);
        } else {
            int value = foods.get(choice);
            int newValue = value + foodInKg;
            foods.put(choice, newValue);
        }
        for(String key : foods.keySet()){
            System.out.println(key + ": " + foods.get(key));
        }
    }

    public void removeFood(String choice, int foodInKg){
        if(!foods.containsKey(choice)){
            System.out.println("Cannot feed with food you don't have.");
        } else {
            int value = foods.get(choice);
            if(value < foodInKg){
                System.out.println("You cannot feed with " + foodInKg + " you only have " + value + " kg's of food.");
            } else {
                int newValue = value - foodInKg;
                foods.put(choice, newValue);
            }
        }
        for(String key : foods.keySet()){
            System.out.println(key + ": " + foods.get(key));
        }
    }
}

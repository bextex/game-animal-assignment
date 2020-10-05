package com.company;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

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
    }

    public void removeFood(String animalName, String choice, int foodInKg){
        /*
        for(Food f : foods.keySet()){ // REMOVE THE FOOD FROM THE LIST WHEN EMPTY
            if(foods.containsValue(0)){
                foods.remove(f);
            }
        }
        */

        for(Food f : foods.keySet()){
            if(!choice.equals(f.getClass().getSimpleName().toLowerCase())){
                System.out.println("You cannot feed with food you don't have!");
                return;
            } else if(choice.equals(f.getClass().getSimpleName().toLowerCase())){
                int value = foods.get(f);
                if(value < foodInKg){
                    System.out.println("You cannot feed with " + foodInKg + " you only have " + value + " kilos of food.");
                    return;
                } else {
                    for(Animal a : animals){
                        if(a.name.equals(animalName)){
                            boolean canEatFood = (a.eatGrain && f.grain || a.eatMeat && f.meat || a.eatVegetable && f.vegetable);
                            if(canEatFood){
                                int newValue = value - foodInKg;
                                foods.put(f, newValue);
                                int newHealth = 10 * foodInKg;
                                a.health = a.health + newHealth;
                                if(newHealth >= 100 || a.health >= 100){
                                    a.health = 100;
                                }
                                System.out.println(a.name + " liked this food. " + (a.gender.equals("female") ? "Her" : "His") + " health improved to " + a.health + ".");
                            } else {
                                System.out.println("URK! " + a.name + " doesn't like this kind of food!");
                            }
                        }
                    }
                }
            }
        }
    }
}

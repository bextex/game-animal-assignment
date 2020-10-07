package com.company;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Player {

    public static ArrayList<Player> players = new ArrayList<>();
    ArrayList<Animal> animals = new ArrayList<>();
    LinkedHashMap<String, Integer> foods = new LinkedHashMap<>();
    Food food;

    public String name;
    public int money = 1000;

    public Player(String name) {
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public Food convertStringToFood(String choice) {
        switch (choice) {
            case "grass" -> this.food = new Grass();
            case "corn" -> this.food = new Corn();
            case "meat" -> this.food = new Meat();
            default -> throw new RuntimeException("Not a valid choice");
        }
        return this.food;
    }

    public void addFood(String choice, int foodInKg) {
        if (!foods.containsKey(choice)) {
            foods.put(choice, foodInKg);
        } else {
            int value = foods.get(choice);
            int newValue = value + foodInKg;
            foods.put(choice, newValue);
        }
    }

    private boolean animalEatFood(Food food, String animalName) {
        for (Animal a : animals) {
            if (a.name.toLowerCase().equals(animalName.toLowerCase())) {
                return (a.eatGrain && food.grain || a.eatMeat && food.meat || a.eatVegetable && food.vegetable);
            }
        }
        return false;
    }

    public boolean removeFood(String animalName, String choice, int foodInKg) {
        boolean okFoodChoice = animalEatFood(convertStringToFood(choice), animalName);

        if(okFoodChoice) {
            for (String key : foods.keySet()) {
                if(foods.get(choice) == null){
                    System.out.println("You can't feed with what you don't have!");
                    return false;
                } else if (choice.equals(key)) {
                    if (foodInKg > foods.get(key)) {
                        System.out.println("You don't have that much food.\n");
                        return false;
                    } else {
                        int value = foods.get(key);
                        value = value - foodInKg;
                        foods.put(key, value);
                        System.out.println("Yummi! " + animalName + " liked that!\n");
                        return true;
                    }
                }
            }
        } else {
            System.out.println("URK! " + animalName + " doesn't like this kind of food!\n");
        }
        return false;
    }

    public void cleanList(){
        int value;
        for(String key : foods.keySet()){ // ERROORRRR!!! When same animal eats two time in a row so the food runs out (2 kg first round and 3 kg second round) And there's total 5 kg.
            value = foods.get(key);
            if(value == 0){
                foods.remove(key);
            }
        }
    }
}


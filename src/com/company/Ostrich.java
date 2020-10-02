package com.company;

public class Ostrich extends Animal { // Ostrich inherent from Animal

    private String name;
    private String gender;
    private boolean eatGrain = true;
    private boolean eatMeat = true;
    private boolean eatVegetable = true;
    private int health;
    public int price = 150;

    public Ostrich(String name, String gender){
        super(name, gender);
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        this.gender = gender.toLowerCase();
        this.health = super.health;
        create(this.name, this.gender, this.health);
    }

    public void create(String name, String gender, int health){
        System.out.println("You have created the ostrich " + name + " with gender " + gender + ". And " +
                (gender.equals("female") ? "she" : "he") + " has " + health + " in health.");
    }

    public void eatsFood(Food food){
        System.out.println("You want to feed " + this.name + " with " + food.name.toLowerCase());
        if(food.grain == eatGrain || food.meat == eatMeat || food.vegetable == eatVegetable){
            System.out.println("And " + this.name + " loves it.");
        } else{
            System.out.println(this.name + " doesn't like this food.");
        }
    }

}

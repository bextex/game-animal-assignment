package com.company;

public class Cow extends Animal { // Cow inherent from Animal

    private String name;
    private String gender;
    private boolean eatGrain = true;
    private boolean eatMeat = false;
    private boolean eatVegetable = true;
    private int health;

    public Cow(String name, String gender){
        super(name, gender);
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        this.gender = gender.toLowerCase();
        this.health = super.health;
        create(this.name, this.gender, this.health);
    }

    // Testing to create a cow with health from superclass Animal
    public void create(String name, String gender, int health){
        System.out.println("You have created the cow " + name + " with gender " + gender + ". And " +
                (gender.equals("female") ? "she" : "he") + " has " + health + " in health.");
    }

    // Testing if the cow can eat the food
    public void eatsFood(Food food){
        System.out.println("You want to feed " + this.name + " with " + food.name.toLowerCase());
        if(food.grain == eatGrain || food.meat == eatMeat || food.vegetable == eatVegetable){
            System.out.println("And " + this.name + " loves it.");
        } else{
            System.out.println(this.name + " doesn't like this food.");
        }
    }

}

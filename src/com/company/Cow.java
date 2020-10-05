package com.company;

public class Cow extends Animal { // Cow inherent from Animal

    private boolean eatGrain = true;
    private boolean eatMeat = false;
    private boolean eatVegetable = true;
    private int health;
    public int price = 100;

    public Cow(String name, String gender){
        super(name, gender);
        super.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        super.gender = gender.toLowerCase();
        this.health = super.health;
        super.price = this.price;
        create(this.name, this.gender, this.health);
    }

    // Testing to create a cow with health from superclass Animal
    public void create(String name, String gender, int health){
        System.out.println("The " + gender + " cow, " + name + ", is now in your possession " + "and " +
                (gender.equals("female") ? "she" : "he") + " has full health " + "(" + health + ").");
    }

    // Testing if the cow can eat the food
    public void eatsFood(Animal animal, Food food, int foodInKg){
        System.out.println("You want to feed " + this.name + " with " + food.name.toLowerCase());
        if(food.grain == eatGrain || food.meat == eatMeat || food.vegetable == eatVegetable){
            System.out.println("And " + this.name + " loves it.");
            int newHealth = 10 * foodInKg;
            if(newHealth >= 100){
                this.health = 100;
            } else {
                this.health = newHealth;
            }
        } else{
            System.out.println(this.name + " doesn't like this food.");
        }
    }

}

package com.company;

public class Horse extends Animal { // Horse inherent from Animal

    private String name;
    private String gender;
    private boolean eatGrain = false;
    private boolean eatMeat = false;
    private boolean eatVegetable = true;
    private int health;
    public int price = 120;

    public Horse(String name, String gender){
        super(name, gender);
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        this.gender = gender.toLowerCase();
        this.health = super.health;
        super.price = this.price;
        create(this.name, this.gender, this.health);
    }

    public void create(String name, String gender, int health){
        System.out.println("The " + gender + " horse, " + name + ", is now in your possession." + "and " +
                (gender.equals("female") ? "she" : "he") + " has full health " + "(" + health + ").");
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

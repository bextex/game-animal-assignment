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
        create(this.name, this.gender, Animal.health);
    }

    // Testing to create a cow with health from superclass Animal
    public void create(String name, String gender, int health){
        System.out.println("You have created the cow " + name + " with gender " + gender + ". And " +
                (gender.equals("female") ? "she" : "he") + " has " + health + " in health.");
    }

    // Testing if the cow can eat the food
    public void eatsFood(Grass grain, Grass meat, Grass vegetable){
        System.out.println("You want to feed " + this.name + " with " + Grass.class.getSimpleName());
        /*if(eatGrain == grain || this.eatMeat == meat || this.eatVegetable == vegetable){
            System.out.println("And " + this.name + " loves it.");
        } else{
            System.out.println(this.name + " doesn't like this food.");
        }*/
    }

}

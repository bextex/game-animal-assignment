package com.company;

public class Cow extends Animal { // Cow inherent from Animal

    private static boolean eatGrain = true;
    private static boolean eatMeat = false;
    private static boolean eatVegetable = true;
    private int health;
    public int price = 100;

    public Cow(String name, String gender){
        super(name, gender);
        super.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        super.gender = gender.toLowerCase();
        this.health = super.health;
        super.price = this.price;
        super.eatGrain = Cow.eatGrain;
        super.eatMeat = Cow.eatMeat;
        super.eatVegetable = Cow.eatVegetable;
        //create(this.name, this.gender, this.health);
    }

    // Testing to create a cow with health from superclass Animal
    /*public void create(String name, String gender, int health){
        System.out.println("The " + gender + " cow, " + name + ", is now in your possession " + "and " +
                (gender.equals("female") ? "she" : "he") + " has full health " + "(" + health + ").");
    }*/
}

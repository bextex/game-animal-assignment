package com.company;

public class Horse extends Animal { // Horse inherent from Animal

    private String name;
    private String gender;
    private double health;
    public int price = 120;
    private static boolean eatGrain = false;
    private static boolean eatMeat = false;
    private static boolean eatVegetable = true;

    public Horse(String name, String gender){
        super(name, gender);
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        this.gender = gender.toLowerCase();
        this.health = super.health;
        super.price = this.price;
        super.eatGrain = Horse.eatGrain;
        super.eatMeat = Horse.eatMeat;
        super.eatVegetable = Horse.eatVegetable;
        //create(this.name, this.gender, this.health);
    }
/*
    public void create(String name, String gender, int health){
        System.out.println("The " + gender + " horse, " + name + ", is now in your possession " + "and " +
                (gender.equals("female") ? "she" : "he") + " has full health " + "(" + health + ").");
    }*/

}

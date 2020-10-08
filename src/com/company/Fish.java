package com.company;

public class Fish extends Animal{ // Fish inherent from Animal

    private String name;
    private String gender;
    private static boolean eatGrain = true;
    private static boolean eatMeat = true;
    private static boolean eatVegetable = false;
    private double health;
    public int price = 50;
    protected int maxAge = 10;
    public int currentAge;

    public Fish(String name, String gender){
        super(name, gender);
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        this.gender = gender.toLowerCase();
        this.health = super.health;
        super.price = this.price;
        super.eatGrain = Fish.eatGrain;
        super.eatMeat = Fish.eatMeat;
        super.eatVegetable = Fish.eatVegetable;
        super.maxAge = this.maxAge;
        super.currentAge = this.currentAge;
    }
}

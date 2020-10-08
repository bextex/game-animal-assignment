package com.company;

public class Cow extends Animal { // Cow inherent from Animal

    private static boolean eatGrain = true;
    private static boolean eatMeat = false;
    private static boolean eatVegetable = true;
    private double health;
    public int price = 100;
    protected int maxAge = 20;
    public int currentAge;

    public Cow(String name, String gender){
        super(name, gender);
        super.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        super.gender = gender.toLowerCase();
        this.health = super.health;
        super.price = this.price;
        super.eatGrain = Cow.eatGrain;
        super.eatMeat = Cow.eatMeat;
        super.eatVegetable = Cow.eatVegetable;
        super.maxAge = this.maxAge;
        super.currentAge = this.currentAge;
    }
}

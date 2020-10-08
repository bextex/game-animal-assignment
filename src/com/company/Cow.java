package com.company;

public class Cow extends Animal { // Cow inherent from Animal

    protected double health;
    protected int currentAge;

    public Cow(String name, String gender){
        super(name, gender);
        super.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        super.gender = gender.toLowerCase();
        super.health = this.health;
        super.price = 100;
        super.eatGrain = true;
        super.eatMeat = false;
        super.eatVegetable = true;
        super.maxAge = 20;
        super.currentAge = this.currentAge;
    }
}

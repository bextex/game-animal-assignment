package com.company;

public class Fish extends Animal{ // Fish inherent from Animal

    protected double health;
    protected int currentAge;

    public Fish(String name, String gender){
        super(name, gender);
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        this.gender = gender.toLowerCase();
        this.health = super.health;
        super.price = 50;
        super.eatGrain = true;
        super.eatMeat = true;
        super.eatVegetable = false;
        super.maxAge = 10;
        super.currentAge = this.currentAge;
    }
}

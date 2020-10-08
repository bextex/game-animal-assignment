package com.company;

public class Horse extends Animal { // Horse inherent from Animal

    protected double health;
    public int currentAge;

    public Horse(String name, String gender){
        super(name, gender);
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        this.gender = gender.toLowerCase();
        super.health = this.health;
        super.price = 120;
        super.eatGrain = false;
        super.eatMeat = false;
        super.eatVegetable = true;
        super.maxAge = 25;
        super.currentAge = this.currentAge;
    }
}

package com.company;

public class Ostrich extends Animal { // Ostrich inherent from Animal

    protected double health;
    public int currentAge;

    public Ostrich(String name, String gender){
        super(name, gender);
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        this.gender = gender.toLowerCase();
        super.health = this.health;
        super.price = 150;
        super.eatGrain = true;
        super.eatMeat = true;
        super.eatVegetable = true;
        super.maxAge = 80;
        super.currentAge = this.currentAge;
    }
}

package com.company;

public class Rabbit extends Animal { // Rabbit inherent from Animal

    protected double health;
    public int currentAge;

    public Rabbit(String name, String gender){
        super(name, gender);
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        this.gender = gender.toLowerCase();
        this.health = super.health;
        super.price = 70;
        super.eatGrain = false;
        super.eatMeat = false;
        super.eatVegetable = true;
        super.maxAge = 8;
        super.currentAge = this.currentAge;
    }
}

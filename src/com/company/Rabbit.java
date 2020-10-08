package com.company;

public class Rabbit extends Animal { // Rabbit inherent from Animal

    private String name;
    private String gender;
    private static boolean eatGrain = false;
    private static boolean eatMeat = false;
    private static boolean eatVegetable = true;
    private double health;
    public int price = 70;
    protected int maxAge = 8;
    public int currentAge;

    public Rabbit(String name, String gender){
        super(name, gender);
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        this.gender = gender.toLowerCase();
        this.health = super.health;
        super.price = this.price;
        super.eatGrain = Rabbit.eatGrain;
        super.eatMeat = Rabbit.eatMeat;
        super.eatVegetable = Rabbit.eatVegetable;
        super.maxAge = this.maxAge;
        super.currentAge = this.currentAge;
    }
}

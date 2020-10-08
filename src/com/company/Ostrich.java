package com.company;

public class Ostrich extends Animal { // Ostrich inherent from Animal

    private String name;
    private String gender;
    private static boolean eatGrain = true;
    private static boolean eatMeat = true;
    private static boolean eatVegetable = true;
    private double health;
    public int price = 150;
    protected int age = 80;

    public Ostrich(String name, String gender){
        super(name, gender);
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        this.gender = gender.toLowerCase();
        this.health = super.health;
        super.price = this.price;
        super.eatGrain = Ostrich.eatGrain;
        super.eatMeat = Ostrich.eatMeat;
        super.eatVegetable = Ostrich.eatVegetable;
        super.age = this.age;
    }
}

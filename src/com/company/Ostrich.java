package com.company;

public class Ostrich extends Animal { // Ostrich inherent from Animal

    private String name;
    private String gender;
    private static boolean eatGrain = true;
    private static boolean eatMeat = true;
    private static boolean eatVegetable = true;
    private int health;
    public int price = 150;

    public Ostrich(String name, String gender){
        super(name, gender);
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        this.gender = gender.toLowerCase();
        this.health = super.health;
        super.price = this.price;
        super.eatGrain = Ostrich.eatGrain;
        super.eatMeat = Ostrich.eatMeat;
        super.eatVegetable = Ostrich.eatVegetable;
        create(this.name, this.gender, this.health);
    }

    public void create(String name, String gender, int health){
        System.out.println("The " + gender + " ostrich, " + name + ", is now in your possession " + "and " +
                (gender.equals("female") ? "she" : "he") + " has full health " + "(" + health + ").");
    }

    public boolean eatFood(Food food, int foodInKg){
        if(food.grain == eatGrain || food.meat == eatMeat || food.vegetable == eatVegetable){
            int newHealth = 10 * foodInKg;
            if(newHealth >= 100){
                this.health = 100;
            } else {
                this.health = newHealth;
            }
            return true;
        }
        return false;
    }

}

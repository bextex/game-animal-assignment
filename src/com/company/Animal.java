package com.company;

public abstract class Animal {
     // Animal class is abstract and cannot be instantiated

    protected String name;
    protected String gender;
    public double health = 100;
    public int price;
    protected boolean eatGrain;
    protected boolean eatMeat;
    protected boolean eatVegetable;

    public Animal(String name, String gender){
        this.name = name;
        this.gender = gender;
    }

    public String getGender(){
        return this.gender;
    }
}

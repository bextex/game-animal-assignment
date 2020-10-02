package com.company;

public abstract class Animal { // Animal class is abstract and cannot be instantiated

    protected String name;
    protected String gender;
    public int health = 100;
    public int price;

    public Animal(String name, String gender){
        this.name = name;
        this.gender = gender;
    }

}

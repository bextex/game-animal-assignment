package com.company;

public abstract class Animal { // Animal class is abstract and cannot be instantiated

    protected String name;
    protected String gender;
    protected static int health = 100;

    public Animal(String name, String gender){
        this.name = name;
        this.gender = gender;
    }

}

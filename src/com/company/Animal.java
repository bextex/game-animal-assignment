package com.company;

public abstract class Animal { // Animal class is abstract and cannot be instantiated

    private String name;
    private String gender;
    protected static int health = 100;

    public Animal(String name, String gender){
        this.name = name;
        this.gender = gender;
    }

}

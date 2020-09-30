package com.company;

public abstract class Food { // // Food class is abstract and cannot be instantiated

    protected String name;
    protected boolean grain;
    protected boolean meat;
    protected boolean vegetable;
    protected int pricePerKg;

    public Food(String name, boolean grain, boolean meat, boolean vegetable, int pricePerKg){
        this.name = name;
        this.grain = grain;
        this.meat = meat;
        this.vegetable = vegetable;
        this.pricePerKg = pricePerKg;
    }

}

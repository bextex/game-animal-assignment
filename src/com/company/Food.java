package com.company;

public abstract class Food { // // Food class is abstract and cannot be instantiated

    protected String name;
    protected boolean grain;
    protected boolean meat;
    protected boolean vegetable;
    protected int pricePerKg;
    protected int kgFood;

    public Food(String name, boolean grain, boolean meat, boolean vegetable, int pricePerKg, int kgFood){
        this.name = name;
        this.grain = grain;
        this.meat = meat;
        this.vegetable = vegetable;
        this.pricePerKg = pricePerKg;
        this.kgFood = kgFood;
    }

}

package com.company;

public abstract class Food { // // Food class is abstract and cannot be instantiated

    private String name;
    private boolean grain;
    private boolean meat;
    private boolean vegetable;

    public Food(String name, boolean grain, boolean meat, boolean vegetable){
        this.name = name;
        this.grain = grain;
        this.meat = meat;
        this.vegetable = vegetable;
    }

}

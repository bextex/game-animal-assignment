package com.company;

public final class Grass extends Food {

    public static String name = "Grass";
    public static boolean grain = false;
    public static boolean meat = false;
    public static boolean vegetable = true;
    public static int pricePerKg = 10;
    public int kgFood;

    public Grass(int kgFood){
        super(name, grain, meat, vegetable, pricePerKg, kgFood);
        Grass.name = super.name;
        Grass.grain = super.grain;
        Grass.meat = super.meat;
        Grass.vegetable = super.vegetable;
        Grass.pricePerKg = super.pricePerKg;
        this.kgFood = super.kgFood;
    }
}

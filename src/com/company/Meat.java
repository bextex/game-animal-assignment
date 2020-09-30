package com.company;

public final class Meat extends Food {

    public static String name = "Meat";
    public static boolean grain = false;
    public static boolean meat = true;
    public static boolean vegetable = false;
    public static int pricePerKg = 15;

    public Meat(){
        super(name, grain, meat, vegetable, pricePerKg);
        Meat.name = super.name;
        Meat.grain = super.grain;
        Meat.meat = super.meat;
        Meat.vegetable = super.vegetable;
        Meat.pricePerKg = super.pricePerKg;
    }

}

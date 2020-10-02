package com.company;

public final class Corn extends Food {

    public static String name = "Corn";
    public static boolean grain = true;
    public static boolean meat = false;
    public static boolean vegetable = false;
    public static int pricePerKg = 12;

    public Corn(int kgFood){
        super(name, grain, meat, vegetable, pricePerKg);
        Corn.name = super.name;
        Corn.grain = super.grain;
        Corn.meat = super.meat;
        Corn.vegetable = super.vegetable;
        Corn.pricePerKg = super.pricePerKg;
    }

}

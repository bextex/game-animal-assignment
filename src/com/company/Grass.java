package com.company;

public final class Grass extends Food {

    public Grass(){
        super.name = "Grass";
        super.grain = false;
        super.meat = false;
        super.vegetable = true;
        super.pricePerKg = 10;
    }
}

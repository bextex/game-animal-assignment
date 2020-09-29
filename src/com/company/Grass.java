package com.company;

public final class Grass extends Food {

    private String name = "Grass";
    private boolean grain = false;
    private boolean meat = false;
    private boolean vegetable = true;

    public Grass(String name, boolean grain, boolean meat, boolean vegetable){
        super(name, grain, meat, vegetable);
        this.name = name;
        this.grain = grain;
        this.meat = meat;
        this.vegetable = vegetable;
    }

    public boolean grassAsFood(){
        return true;
    }


}

package com.company;

public class Main {

    public static void main(String[] args) {

        Cow carola = new Cow("carOLA", "male");
        Fish nemo = new Fish("nemo", "male");
        Horse leyla = new Horse("Leyla", "female");
        Rabbit snurre = new Rabbit("Snurre", "male");
        Ostrich ida = new Ostrich("ida", "female");
        System.out.println();
        Grass grass = new Grass();
        Corn corn = new Corn();
        Meat meat = new Meat();

        carola.eatsFood(meat);
        System.out.println();
        nemo.eatsFood(grass);
        System.out.println();
        leyla.eatsFood(corn);
        System.out.println();
        snurre.eatsFood(grass);
        System.out.println();
        ida.eatsFood(meat);


    }
}

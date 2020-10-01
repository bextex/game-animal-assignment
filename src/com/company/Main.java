package com.company;

public class Main {

    public static void main(String[] args) throws Exception{
/*
        Cow carola = new Cow("carOLA", "male");
        Cow kicki = new Cow("Kicki", "female");
        Fish nemo = new Fish("nemo", "male");
        Fish lollo = new Fish("Lollo", "female");
        Horse leyla = new Horse("Leyla", "female");
        Rabbit snurre = new Rabbit("Snurre", "male");
        Ostrich ida = new Ostrich("ida", "female");
 */
        System.out.println();
        //Grass grass = new Grass();
        //Corn corn = new Corn();
        //Meat meat = new Meat();
        Player player = new Player("Haj");
/*
        carola.eatsFood(meat);
        System.out.println();
        nemo.eatsFood(grass);
        System.out.println();
        leyla.eatsFood(corn);
        System.out.println();
        snurre.eatsFood(grass);
        System.out.println();
        ida.eatsFood(meat);

        System.out.println();
        System.out.println("hello player " + new Player("ErIK").name + " you have " + Player.money + " kr to begin with.");
        System.out.println();
*/
        Game game = new Game();
        Store store = new Store();
        //game.mating(carola, kicki);
        //game.mating(nemo,lollo);

        //game.buyAnimal(player);
        store.buyFood(player);
        store.buyAnimal(player);
        game.feedAnimal(player);


    }
}

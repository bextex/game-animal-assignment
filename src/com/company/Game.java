package com.company;

import java.util.*;

public class Game {
    Random random = new Random();
    Scanner input = new Scanner(System.in);
    ArrayList<Integer> winnerMoney = new ArrayList<>();
    Store store = new Store();
    Player player;
    String causeOfDeath;
    boolean activeRound = true, firstRound = true;
    int round, numOfPlayers;
    int n = 1;

    public Game() {
        getGameInfo();
        System.out.println("Type in number of players (1-4)");
        String choice = Prompt.inputCheck(input.nextLine(), 1, 4);
        setPlayers(choice);
        boolean secret = chooseRound();
        numOfPlayers = Player.players.size();
        do {
            sleep(500);
            currentPlayer();
            System.out.println(round == 1 ? "Last round!" : (secret ? "Secret number of" : round) + " rounds left.");
            System.out.println();
            System.out.println(player.name + " is your turn!");
            playersHolding(player);
            System.out.println("------------------------------------");
            menu();
            String nextStep = (Player.players.size() == 1 ? Prompt.inputCheck(input.nextLine(), 1, 6)
                    : Prompt.inputCheck(input.nextLine(), 1, 7));
            switch (nextStep) {
                case "1" -> store.buyAnimal(player);
                case "2" -> store.buyFood(player);
                case "3" -> player.feedAnimal(player);
                case "4" -> player.mateAnimal(player);
                case "5" -> store.sellAnimal(player);
            }
            if(Player.players.size() >= 2) {
                switch (nextStep){
                    case "6" -> store.sellAnimalToPlayer(player);
                    case "7" -> activeRound = false;
                }
            } else {
                if ("6".equals(nextStep)) {
                    activeRound = false;
                }
            }
            if(numOfPlayers == 1 || Player.players.size() == 1){
                round--;
                numOfPlayers = Player.players.size();
            } else {
                numOfPlayers--;
            }
            if(round <= 0){
                sleep(500);
                System.out.println("---- THE GAME IS OVER ----\n");
                summarizeBelongings();
            }
        } while(round > 0);
    }

    public boolean chooseRound(){
        System.out.println("Do you wanna pick a number of rounds[1] or let it be a secret[2]?");
        String choice = Prompt.inputCheck(input.nextLine(), 1, 2);
        if(choice.equals("1")){
            System.out.println("How many rounds do you want to play (5-30)?");
            String numOfRounds = Prompt.inputCheck(input.nextLine(), 5,30);
            this.round = Integer.parseInt(numOfRounds);
            return false;
        } else{
            this.round = random.nextInt(25) + 5;
        }
        return true;
    }

    public Player currentPlayer(){
        if (!firstRound) {
            if (n == Player.players.size()) {
                n = 1;
            } else {
                n++;
            }
        }
        this.player = Player.players.get(n-1);
        firstRound = false;
        return this.player;
    }

    public void setPlayers(String playerNum){
        int numOfPlayers = Integer.parseInt(playerNum);
        for(int i = 1; i <= numOfPlayers; i++){
            System.out.println("Type in the name of Player " + i + ":");
            String player = input.nextLine();
            Player.players.add(new Player(player));
        }
    }

    public void playersHolding(Player player){
        sleep(1000);
        System.out.println("These are your current holdings:");
        System.out.println("------------------------------------");
        System.out.println("Money: " + player.money + " kr\n");
        System.out.println("Animals:" + (player.animals.size() == 0 ? " You don't own any animals." : ""));
        player.healthDecreasing(player);
        player.animalAging(player);
        player.sickness(player);
        for(Animal a : player.animals){
            System.out.printf("%s - %s %s - %s\n", a.getClass().getSimpleName(), a.name, (a.gender.equals("female") ? "(f)" : "(m)"),
                    (a.health <= 0 || a.currentAge >= a.maxAge ? ("HAS DIED OF " + causeOfDeath + "!!! x_x") : "(age: " + a.currentAge + ", " + "health: " + (int) a.health + ")"));
        }
        System.out.println();
        player.animals.removeIf(a -> a.health <= 0 || a.currentAge >= a.maxAge);
        System.out.println("Foods:" + (player.foods.size() == 0 ? " You don't own any food." : ""));
        for(String key : player.foods.keySet()){
            System.out.println(key + " - " + player.foods.get(key) + " kg");
        }
    }

    public void menu(){
        System.out.println("1. Buy an animal.");
        System.out.println("2. Buy food.");
        System.out.println("3. Feed your animal(s).");
        System.out.println("4. Mate your animals.");
        System.out.println("5. Sell your animal(s).");
        if(Player.players.size() >= 2){
            System.out.println("6. Trade with other players.");
            System.out.println("7. Exit round.");
        } else {
            System.out.println("6. Exit round.");
        }
    }

    public void summarizeBelongings(){
        for(Player p : Player.players){
            for(Animal a : p.animals){
                p.money = (int) (p.money + (a.price * (a.health / 100)));
                winnerMoney.add(p.money);
            }
            System.out.println(p.name + " has a total of " + p.money + " kr.");
        }
        Player.players.sort((Player a, Player b) -> a.money > b.money ? -1 : 1); // Doesn't work.
    }

    private void getGameInfo(){
        System.out.println("\n---- WELCOME TO THE GAME PIGGYBANK ---- \n");
        System.out.println("You can play by yourself or with up to three friends. " +
                "\nThe players can choose to pick the number of rounds playing or get a secret number." +
                "\nYou will buy animals and food, feed your animals so they don't die, mate your animals to get little babies." +
                "\nAnd if you're more than one player, you can trade animals with your fellow players." +
                "\nEach player can pick between 6 choices of actions, but will only be able to make one each turn." +
                "\nAnd they can do so as long as they have money or they can choose to exit." +
                "\nWatch out for unexpected sickness and be aware of your animals health and age." +
                "\nThe player with the most money after the game ends will be the winner. Good luck and make smart choices!");
        System.out.println("\nPRESS ENTER TO START");
        input.nextLine();
    }

    public static void sleep(int ms){
        try {
            Thread.sleep(ms);
        }
        catch(Exception ignore){}
    }
}

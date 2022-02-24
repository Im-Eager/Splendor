package academy.mindswap.game;

import academy.mindswap.cards.Card;
import academy.mindswap.server.ClientConnectionHandler;
import academy.mindswap.utils.PrintToTerminalGame;

import java.util.*;

public class Game implements Runnable {

    private String command;

    private int[] bank;

    private final List<ClientConnectionHandler> players;

    private HashMap<String, Card> table = new HashMap<>();

    private boolean winner;

    private LinkedList<Card> deckTier4 = new LinkedList<Card>();
    private LinkedList<Card> deckTier3 = new LinkedList<Card>();
    private LinkedList<Card> deckTier2 = new LinkedList<Card>();
    private LinkedList<Card> deckTier1 = new LinkedList<Card>();

    public Game(List players, List<Card> deck) {

        Collections.shuffle(players);
        Collections.shuffle(deck);

        this.deckTier1.addAll(deck.stream().filter(t -> t.getTier() == 1).toList());
        this.deckTier2.addAll(deck.stream().filter(t -> t.getTier() == 2).toList());
        this.deckTier3.addAll(deck.stream().filter(t -> t.getTier() == 3).toList());
        this.deckTier4.addAll(deck.stream().filter(t -> t.getTier() == 4).toList());

        this.players = players;

        this.players.forEach(p -> p.setGame(this));
        this.players.forEach(p -> p.setPlayer(new Player(p.getName())));

        run();
    }

    private void gameSetup() {
        PrintToTerminalGame.startScreen();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PrintToTerminalGame.screenSetup();


        fillBank();

        ArrayList<LinkedList<Card>> tiersCardGiver = new ArrayList<>();

        tiersCardGiver.add(deckTier1);
        tiersCardGiver.add(deckTier2);
        tiersCardGiver.add(deckTier3);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                Card cardToGive = tiersCardGiver.get(i).get(j);
                tiersCardGiver.get(i).remove(cardToGive);
                this.table.put("p" + (i + 1) + (j + 1), cardToGive);
            }
        }

        for (int i = 0; i < this.players.size(); i++) {
            Card cardToGive = this.deckTier4.get(i);
            this.deckTier4.remove(cardToGive);
            this.table.put("p4" + (i + 1), cardToGive);
        }
    }

    private void fillBank() {
        int numOfChips = 7;
        if (players.size() == 3) {
            numOfChips -= 2;
        }
        if (players.size() == 2) {
            numOfChips -= 3;
        }
        this.bank = new int[]{numOfChips, numOfChips, numOfChips, numOfChips, numOfChips, 5};
    }

    public void setCommand(String command) {
        players.stream().findFirst().get().send("Cheu");
        this.command = command;
        notifyAll();
    }

    @Override
    public void run() {

        gameSetup();

        ClientConnectionHandler playerPlaying = players.stream().findFirst().get();

        try {

            while (!winner) {
                this.command = null;

                playerPlaying.getPlayer().setPlaying(true);
                players.forEach(p -> p.send("COLOCAR IMPRESSÂO DO BOARD"));
                players.forEach(p -> p.send("COLOCAR IMPRESSÂO DA MÃO RESPECTIVA DE CADA JOGADOR"));
                playerPlaying.send("It is your turn to play! \nWaiting for your command... \nType /help to receive a list of commands.");

                wait();

                playerPlaying.send("Cheguei aqui2");



            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/*
//    public int checkPlayerCards(List<Card> playerHand) {
//
//        return player.getPlayerHand()
//                .stream().mapToInt(Card::getPoints)
//                .sum();
//    }
//
//    public int checkPlayerTokens() {
//        return player.getGems();
//
//    }
//
//    public void checkPlayerPoints() {
//        if (player.getPoints >= 15) {
//            player.win();
//            if (player.getPoints < 15) {
//                Messages.LOSE_MESSAGE();
//            }
//        }
//    }
//
//    public void updatePlayerScore() {
//
//    }

}*/

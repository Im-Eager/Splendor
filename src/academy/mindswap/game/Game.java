package academy.mindswap.game;

import academy.mindswap.cards.Card;
import academy.mindswap.utils.Messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.*;

public class Game implements Runnable {

    private int[] bank;

    private final HashMap<Player, Socket> players;

    private HashMap<String, Card> table;

    private boolean winner;

    private LinkedList<Card> deckTier4;
    private LinkedList<Card> deckTier3;
    private LinkedList<Card> deckTier2;
    private LinkedList<Card> deckTier1;

    public Game(HashMap players, List<Card> deck) {

        Collections.shuffle(deck);

        this.deckTier1.addAll(deck.stream().filter(t -> t.getTier() == 1).toList());
        this.deckTier2.addAll(deck.stream().filter(t -> t.getTier() == 2).toList());
        this.deckTier3.addAll(deck.stream().filter(t -> t.getTier() == 3).toList());
        this.deckTier4.addAll(deck.stream().filter(t -> t.getTier() == 4).toList());

        this.players = players;

        gameSetup();

        run();
    }

    private void gameSetup() {

        fillBank();

        ArrayList<LinkedList<Card>> tiersCardGiver = new ArrayList<>();

        tiersCardGiver.add(deckTier1);
        tiersCardGiver.add(deckTier2);
        tiersCardGiver.add(deckTier3);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                Card cardToGive = tiersCardGiver.get(i).get(j);
                tiersCardGiver.get(i).remove(cardToGive);
                this.table.put("p" + Integer.toString(i + 1) + Integer.toString(j + 1), cardToGive);
            }
        }

        for (int i = 0; i < this.players.size(); i++) {
            Card cardToGive = this.deckTier4.get(i);
            this.deckTier4.remove(cardToGive);
            this.table.put("p4" + Integer.toString(i + 1), cardToGive);
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

    @Override
    public void run() {


        try {
            Socket socket = new Socket();
            String command;
            BufferedReader br = null;

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!winner) {

                Player playerPlaying;
                command = br.readLine();


            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int checkPlayerCards(List<Card> playerHand) {

        return player.getPlayerHand()
                .stream().mapToInt(Card::getPoints)
                .sum();
    }

    public int checkPlayerTokens() {
        return player.getGems();

    }

    public void checkPlayerPoints() {
        if (player.getPoints >= 15) {
            player.win();
            if (player.getPoints < 15) {
                Messages.LOSE_MESSAGE();
            }
        }
    }

    public void updatePlayerScore() {

    }

}

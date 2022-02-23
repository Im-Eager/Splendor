package academy.mindswap.game;

import academy.mindswap.cards.Card;
import academy.mindswap.utils.Messages;

import java.net.Socket;
import java.util.*;

public class Game implements Runnable{

    private int[] bank;

    private final HashMap<Player, Socket> players;

    private HashMap<String, Card> table;

    private LinkedList<Card> deckTier4;
    private LinkedList<Card> deckTier3;
    private LinkedList<Card> deckTier2;
    private LinkedList<Card> deckTier1;

    public Game (HashMap hashMap, List<Card> deck){

        Collections.shuffle(deck);

        this.deckTier1.addAll(deck.stream().filter(t -> t.getTier()==1).toList());
        this.deckTier2.addAll(deck.stream().filter(t -> t.getTier()==2).toList());
        this.deckTier3.addAll(deck.stream().filter(t -> t.getTier()==3).toList());
        this.deckTier4.addAll(deck.stream().filter(t -> t.getTier()==4).toList());

        this.players = hashMap;

        gameSetup();

        run();
    }

    private void gameSetup(){

        fillBank();

        ArrayList<LinkedList<Card>> tiersCardGiver = new ArrayList<>();

        tiersCardGiver.add(deckTier1);
        tiersCardGiver.add(deckTier2);
        tiersCardGiver.add(deckTier3);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                Card cardToGive = tiersCardGiver.get(i).get(j);
                tiersCardGiver.get(i).remove(cardToGive);
                table.put("p" + Integer.toString(i + 1) + Integer.toString(j + 1), cardToGive);
            }
        }

        for (int i = 0; i < players.size(); i++) {
            Card cardToGive = deckTier4.get(i);
            deckTier4.remove(cardToGive);
            table.put("p4" + Integer.toString(i + 1), cardToGive);
        }
    }

    private void fillBank(){
        int numOfChips = 7;
        if (players.size() == 3){
            numOfChips -= 2;
        }
        if (players.size() == 2){
            numOfChips -= 3;
        }
        this.bank = new int[]{numOfChips,numOfChips,numOfChips,numOfChips,numOfChips,5};
    }


    @Override
    public void run() {

    }

    public int checkPlayerCards(List<Card> playerHand){

        return player.getPlayerHand()
                .stream().mapToInt(Card::getPoints)
                .sum();
    }

    public int checkPlayerTokens(){
        return player.getGems();

    }

    public void checkPlayerPoints(){
        if (player.getPoints >= 15){
            player.win();
            if(player.getPoints < 15){
                Messages.LOSE_MESSAGE();
            }
        }
    }

    public void updatePlayerScore(){

    }

}

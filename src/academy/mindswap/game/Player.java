package academy.mindswap.game;

import academy.mindswap.cards.Card;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Player {

    private String name;
    private LinkedList<Card> playerReservedCards;
    private int[] ownedMines;
    private int[] bank;
    private int score;


    public Player(String name){
        this.name = name;
        this.score = 0;
        this.ownedMines = new int[]{0,0,0,0,0};
        this.bank = new int[]{0,0,0,0,0,0};

    }

    public Card reserveCard(Card card){
        gold ++;
        return card;
    }

    public Card buyCard(Card card){
        return card;
    }

    public void grabGems(){



    }

    public Card[] getPlayerReservedCards() {
        Card card = reserveCard();
        for (int i = 0; i <playerReservedCards.length; i++) {
            if(playerReservedCards[i] == null){
                playerReservedCards[i] = card;
            }else {
                System.out.println("You cannot reserve more cards");
                return playerReservedCards;
            }
        }
        return playerReservedCards;
    }

    public List<Card> getPlayerHand() {

        return playerHand;
    }

    public int getGems() {
        return gems;
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}

package academy.mindswap.game;

import academy.mindswap.cards.Card;

import java.util.Iterator;
import java.util.List;

public class Player implements Iterable{

    Card[] playerReservedCards = new Card[3];
    List<Card> playerHand;
    private int gems;
    private int gold;
    private String name;


    public Player(String name){
        this.name = name;
    }

    public void reserveCard(){
        gold ++;

    }

    public void buyCard(){

    }

    public void grabGems(){


    }

    public Card[] getPlayerReservedCards() {
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

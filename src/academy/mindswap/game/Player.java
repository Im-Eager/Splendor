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

package academy.mindswap;

import academy.mindswap.Cards.Card;

import java.util.Iterator;
import java.util.List;

public class Player implements Iterable{

    Card[] playerReservedCards = new Card[3];
    List<Card> playerHand;
    private int gold;


    public Player(){
        String name = "player 1";
    }

    public void reserveCard(){
        gold ++;

    }

    public void buyCard(){

    }

    public void grabGems(){




    }


    @Override
    public Iterator iterator() {
        return null;
    }
}

package academy.mindswap.game;

import academy.mindswap.cards.Card;

import java.util.LinkedList;

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
        //Position of bank index[0] = (W)hite, index[1] = Bl(U)e, index[2] = (G)reen, index[3] = (R)ed, index[4] = Blac(K), index[5] = Go(L)d

    }

    public void reserveCard(Card card){
        this.bank[5]+=1;
        playerReservedCards.add(card);
    }

    public Card buyCard(Card card){
        return card;
    }

    public void grabGems(int[] grabbedGems){
        for (int i = 0; i < grabbedGems.length; i++) {
            bank[i] += grabbedGems[i];
        }
    }



    /*public List<Card> getPlayerHand() {

        return playerHand;
    }

    public int getGems() {
        return gems;
    }

    @Override
    public Iterator iterator() {
        return null;
    }*/
}

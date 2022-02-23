package academy.mindswap.game;

import academy.mindswap.cards.Card;
import academy.mindswap.utils.Messages;

import java.util.Arrays;
import java.util.LinkedList;

public class Player {

    private String name;
    private LinkedList<Card> playerReservedCards;
    private int[] ownedMines;
    private int[] bank;
    private int score;
    private boolean playerCanBuy;


    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.ownedMines = new int[]{0, 0, 0, 0, 0};
        this.bank = new int[]{0, 0, 0, 0, 0, 0};
        //Position of bank index[0] = (W)hite, index[1] = Bl(U)e, index[2] = (G)reen, index[3] = (R)ed, index[4] = Blac(K), index[5] = Go(L)d

    }

    public void reserveCard(Card card) {
        if( playerReservedCards.size() == 3){
            System.out.println(Messages.CANT_RESERVE);
        }
        this.bank[5] += 1;
        playerReservedCards.add(card);
    }


    public void grabGems(int[] grabbedGems) {

        int[] temp = bank;

        for (int i = 0; i < grabbedGems.length; i++) {
            temp[i] += grabbedGems[i];
        }
        int bankTotal = Arrays.stream(temp).reduce(0, Integer::sum);

        if (bankTotal > 10){
            System.out.println(Messages.MORE_THAN_10);
            return;
        }
        bank = temp;

    }


    public void buyCard(Card card) {

        if (canBuyWhite(card) && canBuyBlue(card) && canBuyGreen(card) && canBuyRed(card) && canBuyBlack(card)) {
            playerCanBuy = true;
        }

        if (playerCanBuy) {
            this.score += card.getPoints();  //score increased directly on buy
        }
        System.out.println(Messages.CANT_BUY);
    }


    private boolean canBuyWhite(Card card) {
        if (card.getCostWhite() <= ownedMines[0]
                || card.getCostWhite() <= ownedMines[0] + bank[0]
                || card.getCostWhite() <= ownedMines[0] + bank[0] + bank[5]) {
            return true;
        }
        return false;
    }

    private boolean canBuyBlue(Card card) {
        if (card.getCostBlue() <= ownedMines[1]
                || card.getCostBlue() <= ownedMines[1] + bank[1]
                || card.getCostBlue() <= ownedMines[1] + bank[1] + bank[5]) {
            return true;
        }
        return false;
    }

    private boolean canBuyGreen(Card card) {
        if (card.getCostGreen() <= ownedMines[2]
                || card.getCostGreen() <= ownedMines[2] + bank[2]
                || card.getCostGreen() <= ownedMines[2] + bank[2] + bank[5]) {
            return true;
        }
        return false;
    }

    private boolean canBuyRed(Card card) {
        if (card.getCostRed() <= ownedMines[3]
                || card.getCostRed() <= ownedMines[3] + bank[3]
                || card.getCostRed() <= ownedMines[3] + bank[3] + bank[5]) {
            return true;
        }
        return false;
    }

    private boolean canBuyBlack(Card card) {
        if (card.getCostBlack() <= ownedMines[4]
                || card.getCostBlack() <= ownedMines[4] + bank[4]
                || card.getCostBlack() <= ownedMines[4] + bank[4] + bank[5]) {
            return true;
        }
        return false;
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

/**
 * @(#)Player.java        1.0 28/02/2022
 *
 * Copyright© MindSwap Academy - Diogo Noronha, Luis Faria, Ricardo Paiva, Tiago Miranda
 * All rights reserved.
 *
 * This software was produced to become our first group project.
 */
package academy.mindswap.game;

import academy.mindswap.cards.Lord;
import academy.mindswap.cards.Mine;
import academy.mindswap.utils.Messages;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Player {

    private String name;
    private LinkedList<Mine> playerReservedCards;
    private Lord ownedLord;
    private int[] ownedMines;
    private int[] bank;
    private int score;
    private boolean playerCanBuy;
    private boolean hasPlayed;
    private boolean isPlaying;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.ownedMines = new int[] {0, 0, 0, 0, 0};
        this.bank = new int[] {0, 0, 0, 0, 0, 0};
        this.hasPlayed = false;
        this.playerReservedCards = new LinkedList<Mine>();
        //Position of bank index[0] = (W)hite, index[1] = Bl(U)e, index[2] = (G)reen, index[3] = (R)ed, index[4] = Blac(K), index[5] = Go(L)d

    }

//   public void reserveCard(String positionOfCard) {
//
//        if( playerReservedCards.size() == 3){
//            System.out.println(Messages.CANT_RESERVE);
//            return;
//        }
//        this.bank[5] += 1;
//        playerReservedCards.add();
        // remove card from table
        // add another card
//    }


    public void grabGems(String gems) {

        int[] temp = bank;

        for (int i = 0; i < gems.length(); i++) {
            temp[i] = gems.charAt(i);
        }

        if (Arrays.stream(temp).reduce(0, Integer::sum) > 10){
            System.out.println(Messages.MORE_THAN_10);
            return;
        }
        this.bank = temp;
    }


//    public void buyCard(String card) {
//
//        card.
//
//
//        if (canBuyWhite(card) && canBuyBlue(card) && canBuyGreen(card) && canBuyRed(card) && canBuyBlack(card)) {
//            this.score += card.getPoints();  //score increased directly on buy
//            this.hasPlayed = true;
//            return;
//        }
//
//        System.out.println(Messages.CANT_BUY);
//    }


/*    private boolean canBuyWhite(Card card) {
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
    }*/

    private String win(){
        return score >= 15 ? Messages.I_WIN_MOTHERFUCKERS : Messages.KEEP_PLAYING;
    }

    public void increaseGold(){
        this.bank[5] += 1;
    }

    public void setPlaying(Boolean isPlaying){
        this.isPlaying = isPlaying;
    }

    public boolean isPlaying(){
        return isPlaying;
    }

    public int getScore() {
        return score;
    }

    public int[] getOwnedMines() {
        return ownedMines;
    }

    public int[] getBank() {
        return this.bank;
    }

    public String getName() {
        return name;
    }

    public List<Mine> getReservedCards(){
        return playerReservedCards;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setBank(int[] bank) {
        this.bank = bank;
    }


}

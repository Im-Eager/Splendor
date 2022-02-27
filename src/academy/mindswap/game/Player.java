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

public class Player {

    private final String name;
    private LinkedList<Mine> playerReservedCards;
    private Lord ownedLord;
    private int[] ownedMines;
    private int[] bank;
    private int score;
    private boolean isPlaying;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.ownedMines = new int[] {0, 0, 0, 0, 0};
        this.bank = new int[] {0, 0, 0, 0, 0, 0};
        this.playerReservedCards = new LinkedList<>();
        //Position of bank index[0] = (W)hite, index[1] = Bl(U)e, index[2] = (G)reen, index[3] = (R)ed, index[4] = Blac(K), index[5] = Go(L)d
    }

    public void setGold(int amount){
        this.bank[5] += amount;
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

    public LinkedList<Mine> getReservedCards(){
        return playerReservedCards;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setBank(int[] bank) {
        this.bank = bank;
    }

    public void setReservedCard (Mine mine){
        this.playerReservedCards.add(mine);
    }

    public void setOwnedMines(int index){
        this.ownedMines[index] += 1;
    }
}

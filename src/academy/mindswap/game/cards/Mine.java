/**
 * @(#)Mine.java        1.0 28/02/2022
 *
 * Copyright© MindSwap Academy - Diogo Noronha, Luis Faria, Ricardo Paiva, Tiago Miranda
 * All rights reserved.
 *
 * This software was produced to become our first group project.
 */

package academy.mindswap.game.cards;

public class Mine extends Card{

    protected String color;

    public Mine(int costWhite, int costBlue, int costGreen, int costRed, int costBlack, int points, int tier, String color){
        super(costWhite, costBlue, costGreen, costRed, costBlack, points, tier);
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}

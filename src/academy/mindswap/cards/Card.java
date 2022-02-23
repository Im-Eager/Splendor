package academy.mindswap.cards;

public abstract class Card {
    protected int costWhite;
    protected int costBlue;
    protected int costGreen;
    protected int costRed;
    protected int costBlack;
    protected int points;
    protected int tier; //levels High 3, Medium 2, Low 1
    protected String color;

    public Card(int costWhite, int costBlue, int costGreen, int costRed, int costBlack, int points, int tier, String color){
        this.costWhite = costWhite;
        this.costBlue = costBlue;
        this.costGreen = costGreen;
        this.costRed = costRed;
        this.costBlack = costBlack;
        this.points = points;
        this.tier = tier;
        this.color = color;
    }

    public int getCostWhite() {
        return costWhite;
    }

    public int getCostBlue() {
        return costBlue;
    }

    public int getCostGreen() {
        return costGreen;
    }

    public int getCostRed() {
        return costRed;
    }

    public int getCostBlack() {
        return costBlack;
    }

    public int getPoints() {
        return points;
    }

    public int getTier() {
        return tier;
    }

    public String getColor() {
        return color;
    }
}
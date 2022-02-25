package academy.mindswap.cards;

public abstract class Card {
    protected int points;
    protected int tier; //levels Lord 4, High 3, Medium 2, Low 1
    protected String color;
    protected int[] cost;

    //Position of bank index[0] = (W)hite, index[1] = Bl(U)e, index[2] = (G)reen, index[3] = (R)ed, index[4] = Blac(K), index[5] = Go(L)d

    public Card(int costWhite, int costBlue, int costGreen, int costRed, int costBlack, int points, int tier) {
        this.cost = new int[]{costWhite, costBlue, costGreen, costRed, costBlack};
        this.points = points;
        this.tier = tier;
    }

    public int[] getCost(){
        return this.cost;
    }

    public int getPoints() {
        return this.points;
    }

    public int getTier() {
        return tier;
    }
}
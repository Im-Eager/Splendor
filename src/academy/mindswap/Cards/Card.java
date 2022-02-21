package academy.mindswap.Cards;

public abstract class Card {
    protected int cost;
    protected int points;
    protected int tier; //levels High 3, Medium 2, Low 1

    public Card(int cost, int points, int tier){
        this.cost = cost;
        this.points = points;
        this.tier = tier;
    }

    public int getCost() {
        return cost;
    }

    public int getPoints() {
        return points;
    }

    public int getTier() {
        return tier;
    }


}

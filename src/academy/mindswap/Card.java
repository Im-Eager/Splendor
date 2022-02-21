package academy.mindswap;

public class Card {
    private int cost;
    private int points;
    private String color;
    private int tier; //levels High 3, Medium 2, Low 1

    public Card(int cost, int points, String color, int tie){
        this.cost = cost;
        this.points = points;
        this.color = color;
        this.tier = tier;
    }

    public int getCost() {
        return cost;
    }

    public int getPoints() {
        return points;
    }

    public String getColor() {
        return color;
    }

    public int getTier() {
        return tier;
    }


}

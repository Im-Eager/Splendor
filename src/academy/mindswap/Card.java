package academy.mindswap;

public class Card {
    private int cost;
    private int points;
    String color;
    String tear; //levels High, Medium, Low

    public Card(int cost, int points, String color, String tear){
        this.cost = cost;
        this.points = points;
        this.color = color;
        this.tear = tear;
    }

}

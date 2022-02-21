package academy.mindswap.Cards;

public class Mine extends Card{

    protected String color;

    public Mine(int cost, int points, int tier, String color){
        super(cost, points, tier);
        this.color = color;
    }


}

package academy.mindswap.cards;

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

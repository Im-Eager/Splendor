package academy.mindswap.game.decks;

import academy.mindswap.cards.Card;
import academy.mindswap.cards.Lord;
import academy.mindswap.cards.Mine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OriginalDeck {

    private final List<Card> cards;

    public OriginalDeck(){
        this.cards = new ArrayList<Card>(Arrays.asList(
                //TIER 1

                new Mine(1, 1, 1, 1,0,0, 1, "Black"),
                new Mine(1, 2, 1, 1,0,0, 1, "Black"),
                new Mine(2, 2, 0, 1,0,0, 1, "Black"),
                new Mine(0, 0, 1, 3,1,0, 1, "Black"),
                new Mine(0, 0, 2, 1,0,0, 1, "Black"),
                new Mine(2, 0, 2, 0,0,0, 1, "Black"),
                new Mine(0, 0, 3, 0,0,0, 1, "Black"),
                new Mine(0, 4, 0, 0,0,1, 1, "Black"),

                new Mine(1, 0, 1, 1,0,0, 1, "Blue"),
                new Mine(1, 0, 1, 2,1,0, 1, "Blue"),
                new Mine(1, 0, 2, 2,0,0, 1, "Blue"),
                new Mine(0, 1, 3, 1,0,0, 1, "Blue"),
                new Mine(1, 0, 0, 0,2,0, 1, "Blue"),
                new Mine(0, 0, 2, 0,2,0, 1, "Blue"),
                new Mine(0, 0, 0, 0,3,0, 1, "Blue"),
                new Mine(0, 0, 0, 4,0,1, 1, "Blue"),

                new Mine(0, 1, 1, 1,1,0, 1, "White"),
                new Mine(0, 1, 2, 1,1,0, 1, "White"),
                new Mine(0, 2, 2, 0,1,0, 1, "White"),
                new Mine(3, 1, 0, 0,1,0, 1, "White"),
                new Mine(0, 0, 0, 2,1,0, 1, "White"),
                new Mine(0, 2, 0, 0,2,0, 1, "White"),
                new Mine(0, 3, 0, 0,0,0, 1, "White"),
                new Mine(0, 0, 4, 0,0,1, 1, "White"),

                new Mine(1, 1, 0, 1,1,0, 1, "Green"),
                new Mine(1, 1, 0, 1,2,0, 1, "Green"),
                new Mine(0, 1, 0, 2,2,0, 1, "Green"),
                new Mine(1, 3, 1, 0,0,0, 1, "Green"),
                new Mine(2, 1, 0, 0,0,0, 1, "Green"),
                new Mine(0, 2, 0, 2,0,0, 1, "Green"),
                new Mine(0, 0, 0, 3,0,0, 1, "Green"),
                new Mine(0, 0, 0, 0,4,1, 1, "Green"),

                new Mine(1, 1, 1, 0,1,0, 1, "Red"),
                new Mine(2, 1, 1, 0,1,0, 1, "Red"),
                new Mine(2, 0, 1, 0,2,0, 1, "Red"),
                new Mine(1, 0, 0, 1,3,0, 1, "Red"),
                new Mine(0, 2, 1, 0,0,0, 1, "Red"),
                new Mine(2, 0, 0, 2,0,0, 1, "Red"),
                new Mine(3, 0, 0, 0,0,0, 1, "Red"),
                new Mine(4, 0, 0, 0,0,1, 1, "Red"),

                //TIER 2

                new Mine(3, 2, 2, 0,0,1, 2, "Black"),
                new Mine(3, 0, 3, 0,2,1, 2, "Black"),
                new Mine(0, 1, 4, 2,0,2, 2, "Black"),
                new Mine(0, 0, 5, 3,0,2, 2, "Black"),
                new Mine(5, 0, 0, 0,0,2, 2, "Black"),
                new Mine(0, 0, 0, 0,6,3, 2, "Black"),

                new Mine(0, 2, 2, 3,0,1, 2, "Blue"),
                new Mine(0, 2, 3, 0,3,1, 2, "Blue"),
                new Mine(5, 3, 0, 0,0,2, 2, "Blue"),
                new Mine(2, 0, 0, 1,4,2, 2, "Blue"),
                new Mine(0, 5, 0, 0,0,2, 2, "Blue"),
                new Mine(0, 6, 0, 0,0,3, 2, "Blue"),

                new Mine(0, 0, 3, 2,2,1, 2, "White"),
                new Mine(2, 3, 0, 3,0,1, 2, "White"),
                new Mine(0, 0, 1, 4,2,2, 2, "White"),
                new Mine(0, 0, 0, 5,3,2, 2, "White"),
                new Mine(0, 0, 0, 5,0,2, 2, "White"),
                new Mine(6, 0, 0, 0,0,3, 2, "White"),

                new Mine(3, 0, 2, 3,0,1, 2, "Green"),
                new Mine(2, 3, 0, 0,2,1, 2, "Green"),
                new Mine(4, 2, 0, 0,1,2, 2, "Green"),
                new Mine(0, 5, 3, 0,0,2, 2, "Green"),
                new Mine(0, 0, 5, 0,0,2, 2, "Green"),
                new Mine(0, 0, 6, 0,0,3, 2, "Green"),

                new Mine(2, 0, 0, 2,3,1, 2, "Red"),
                new Mine(0, 3, 0, 2,3,1, 2, "Red"),
                new Mine(1, 4, 2, 0,0,2, 2, "Red"),
                new Mine(3, 0, 0, 0,5,2, 2, "Red"),
                new Mine(0, 0, 0, 0,5,2, 2, "Red"),
                new Mine(0, 0, 0, 6,0,3, 2, "Red"),

                //TIER 3

                new Mine(3, 3, 5, 3,0,3, 3, "Black"),
                new Mine(0, 0, 0, 7,0,4, 3, "Black"),
                new Mine(0, 0, 3, 6,3,4, 3, "Black"),
                new Mine(0, 0, 0, 7,3,5, 3, "Black"),

                new Mine(3, 0, 3, 3,5,3, 3, "Blue"),
                new Mine(7, 0, 0, 0,0,4, 3, "Blue"),
                new Mine(6, 3, 0, 0,3,4, 3, "Blue"),
                new Mine(7, 3, 0, 0,0,5, 3, "Blue"),

                new Mine(0, 3, 3, 5,3,3, 3, "White"),
                new Mine(0, 0, 0, 0,7,4, 3, "White"),
                new Mine(3, 0, 0, 3,6,4, 3, "White"),
                new Mine(3, 0, 0, 0,7,5, 3, "White"),

                new Mine(5, 3, 0, 3,3,3, 3, "Green"),
                new Mine(0, 7, 0, 0,0,4, 3, "Green"),
                new Mine(3, 6, 3, 0,0,4, 3, "Green"),
                new Mine(0, 7, 3, 0,0,5, 3, "Green"),

                new Mine(3, 5, 3, 0,3,3, 3, "Red"),
                new Mine(0, 0, 7, 0,3,4, 3, "Red"),
                new Mine(0, 3, 6, 3,0,4, 3, "Red"),
                new Mine(0, 0, 7, 3,0,5, 3, "Red"),

                //Lords

                new Lord(0, 0, 4,4, 0),
                new Lord(0, 4, 4,0, 0),
                new Lord(4, 4, 0,0, 0),
                new Lord(0, 0, 0,4, 4),
                new Lord(4, 0, 0,0, 4),
                new Lord(3, 3, 0,0, 3),
                new Lord(3, 3, 3,0, 0),
                new Lord(0, 0, 3,3, 3),
                new Lord(0, 3, 3,3, 0),
                new Lord(3, 0, 0,3, 3)));

    }

    public List<Card> getDeck(){
        return this.cards;
    }
}

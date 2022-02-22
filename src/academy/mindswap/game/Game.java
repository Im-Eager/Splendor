package academy.mindswap.game;

import academy.mindswap.cards.Card;
import academy.mindswap.game.decks.OriginalDeck;
import academy.mindswap.utils.Messages;

import java.net.Socket;
import java.util.*;
import java.util.stream.Collectors;

public class Game implements Runnable{

    private final HashMap<Player, Socket> hashMap;

    private OriginalDeck deck;

    private HashMap<String, Card> tableTier4;
    private HashMap<String, Card> tableTier3;
    private HashMap<String, Card> tableTier2;
    private HashMap<String, Card> tableTier1;

    private List<Card> deckTier4;
    private List<Card> deckTier3;
    private List<Card> deckTier2;
    private List<Card> deckTier1;

    public Game (HashMap hashMap, List<Card> deck){

        Collections.shuffle(deck);

        this.deckTier1 = deck.stream().filter(t -> t.getTier()==1).collect(Collectors.toList());
        this.deckTier2 = deck.stream().filter(t -> t.getTier()==2).collect(Collectors.toList());
        this.deckTier3 = deck.stream().filter(t -> t.getTier()==3).collect(Collectors.toList());
        this.deckTier4 = deck.stream().filter(t -> t.getTier()==4).collect(Collectors.toList());

        this.hashMap = hashMap;
        gameSetup();
        run();
    }

    private void gameSetup(){

        for (int i = 0; i < 3; i++) {

        }

    }


    @Override
    public void run() {

    }

    public int checkPlayerCards(List<Card> playerHand){

        return player.getPlayerHand()
                .stream().mapToInt(Card::getPoints)
                .sum();
    }

    public int checkPlayerTokens(){
        return player.getGems();

    }

    public void checkPlayerPoints(){
        if (player.getPoints >= 15){
            player.win();
            if(player.getPoints < 15){
                Messages.LOSE_MESSAGE();
            }
        }
    }

    public void updatePlayerScore(){

    }

}

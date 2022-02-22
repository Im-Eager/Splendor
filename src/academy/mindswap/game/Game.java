package academy.mindswap.game;

import academy.mindswap.cards.Card;
import academy.mindswap.utils.Messages;

import java.net.Socket;
import java.util.HashMap;
import java.util.List;

public class Game implements Runnable{

    private final HashMap<String, Socket> hashMap;
    private Player player;

    public Game (HashMap hashMap){
        this.hashMap = hashMap;
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

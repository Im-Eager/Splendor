package academy.mindswap.game;

import academy.mindswap.utils.Messages;

import java.net.Socket;
import java.util.HashMap;

public class Game implements Runnable{

    private final HashMap<String, Socket> hashMap;

    public Game (HashMap hashMap){
        this.hashMap = hashMap;
    }

    @Override
    public void run() {

    }

    public void checkPlayerCards(){

    }

    public void checkPlayerTokens(){

    }

    public void checkPlayerPoints(){
        if (player.getPoints >= 15){
            player.win();
            if(player.getPoints < 15){
                Messages.loseMessage();
            }
        }
    }

    public void updatePlayerScore(){

    }

}

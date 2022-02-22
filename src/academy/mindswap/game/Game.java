package academy.mindswap.game;

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

}

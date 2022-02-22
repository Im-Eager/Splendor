package academy.mindswap.server;

import academy.mindswap.game.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {

    private ServerSocket serverSocket;
    private HashMap<String, Socket> clientConnections;
    private int port;
    private int numberOfConnections = 0;

    public Server () throws IOException {
        this.port = 8080;
        start();
    }

    private void start() throws IOException {
        //FOR NOW ONLY RUNS A GAME AT THE TIME AND ONLY LAUNCH WITH 4 PLAYERS
        this.serverSocket = new ServerSocket(this.port);
        this.clientConnections = new HashMap<String, Socket>();
        while (true){
            ++numberOfConnections;
            acceptConnection();
            if(numberOfConnections == 4){
                launchGame(clientConnections);
                numberOfConnections = 0;
            }
        }
    }

    public void acceptConnection() throws IOException {
        Socket clientSocket = serverSocket.accept();
        clientConnections.put("Player" + numberOfConnections, clientSocket);
        System.out.println("Player" + numberOfConnections);
    }

    public void launchGame (HashMap hashMap){
        Thread gameRunner = new Thread(new Game(hashMap));
    }
}
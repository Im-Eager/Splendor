package academy.mindswap.server;

import academy.mindswap.game.Game;
import academy.mindswap.game.Player;
import academy.mindswap.game.decks.OriginalDeck;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private ServerSocket serverSocket;
    private ExecutorService service;
    private final List<ClientConnectionHandler> clients;

    public Server() throws IOException {
        clients = new CopyOnWriteArrayList<>();
        start(8080);
    }

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        service = Executors.newCachedThreadPool();
        int numberOfConnections = 0;

        while (true) {
            acceptConnection(numberOfConnections);
            ++numberOfConnections;

            if (clients.size() == 4){
                launchGame(clients);
            }
        }
    }

    public void launchGame (List<ClientConnectionHandler> playersList){
        System.out.println("Game starting.");
        Thread gameRunner = new Thread(new Game(playersList, new OriginalDeck().getDeck()));
    }

    public void acceptConnection(int numberOfConnections) throws IOException {
        Socket clientSocket = serverSocket.accept();
        ClientConnectionHandler clientConnectionHandler =
                new ClientConnectionHandler(clientSocket,
                        "Player" + numberOfConnections);
        service.submit(clientConnectionHandler);
    }

    private void addClient(ClientConnectionHandler clientConnectionHandler) {
        clients.add(clientConnectionHandler);
        clientConnectionHandler.send("Welcome player.");
        broadcast(clientConnectionHandler.getName(), "A new player has joined.");
    }

    public void broadcast(String name, String message) {
        clients.stream()
                .filter(handler -> !handler.getName().equals(name))
                .forEach(handler -> handler.send(name + ": " + message));
    }


    public String listClients() {
        StringBuffer buffer = new StringBuffer();
        clients.forEach(client -> buffer.append(client.getName()).append("\n"));
        return buffer.toString();
    }

    public void removeClient(ClientConnectionHandler clientConnectionHandler) {
        clients.remove(clientConnectionHandler);

    }

    public Optional<ClientConnectionHandler> getClientByName(String name) {
        return clients.stream()
                .filter(clientConnectionHandler -> clientConnectionHandler.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public class ClientConnectionHandler implements Runnable {

        private String name;
        private Socket clientSocket;
        private BufferedWriter out;
        private String message;
        private Player player;

        public ClientConnectionHandler(Socket clientSocket, String name) throws IOException {
            this.player = new Player(name);
            this.clientSocket = clientSocket;
            this.name = name;
            this.out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        }

        @Override
        public void run() {
            addClient(this);
            try {
                Scanner in = new Scanner(clientSocket.getInputStream());
                while (in.hasNext()) {
                    message = in.nextLine();
                    /*if (isCommand(message)) {
                        dealWithCommand(message);
                        continue;
                    }*/
                    if (message.equals("")) {
                        continue;
                    }

                    broadcast(name, message);
                }
            } catch (IOException e) {
                System.err.println("Messages.CLIENT_ERROR + e.getMessage()");
            } finally {
                removeClient(this);
            }
        }

        private boolean isCommand(String message) {
            return message.startsWith("/");
        }

        /*private void dealWithCommand(String message) throws IOException {
            String description = message.split(" ")[0];
            Command command = Command.getCommandFromDescription(description);

            if (command == null) {
                out.write("Messages.NO_SUCH_COMMAND");
                out.newLine();
                out.flush();
                return;
            }

            command.getHandler().execute(Server.this, this);
        }*/

        public void send(String message) {
            try {
                out.write(message);
                out.newLine();
                out.flush();
            } catch (IOException e) {
                removeClient(this);
                e.printStackTrace();
            }
        }

        public void close() {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String getName() {
            return name;
        }

        public String getMessage() {
            return message;
        }

        public Player getPlayer() {
            return player;
        }
    }
}



/*
package academy.mindswap.server;

import academy.mindswap.game.Player;
import academy.mindswap.game.decks.OriginalDeck;
import academy.mindswap.game.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {

    private ServerSocket serverSocket;
    private HashMap<Player, Socket> clientConnections;
    private int port;
    private int numberOfConnections = 0;

    public Server () throws IOException {
        this.port = 8080;
        start();
    }

    private void start() throws IOException {
        //FOR NOW ONLY RUNS A GAME AT THE TIME AND ONLY LAUNCH WITH 4 PLAYERS
        this.serverSocket = new ServerSocket(this.port);
        this.clientConnections = new HashMap<Player, Socket>();
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
        String player = "Player" + numberOfConnections;
        clientConnections.put(new Player(player), clientSocket);
        System.out.println("Player " + numberOfConnections + " connected.");
    }

    public void launchGame (HashMap hashMap){
        System.out.println("Game starting.");
        Thread gameRunner = new Thread(new Game(hashMap, new OriginalDeck().getDeck()));
    }
}*/

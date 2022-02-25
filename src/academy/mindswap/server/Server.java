package academy.mindswap.server;

import academy.mindswap.game.Game;
import academy.mindswap.game.decks.OriginalDeck;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private ServerSocket serverSocket;
    private ExecutorService service;
    private ExecutorService games;
    private final List<ClientConnectionHandler> clients;


    public Server() throws IOException {
        clients = new CopyOnWriteArrayList<>();
        start(8080);
    }

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        games = Executors.newCachedThreadPool();
        service = Executors.newCachedThreadPool();
        int numberOfConnections = 0;

        while (true) {
            ++numberOfConnections;
            acceptConnection(numberOfConnections);
            if (numberOfConnections == 4) {
                launchGame(clients);
            }
        }
    }

    public void acceptConnection(int numberOfConnections) throws IOException {
        Socket clientSocket = serverSocket.accept();
        ClientConnectionHandler clientConnectionHandler =
                new ClientConnectionHandler(clientSocket,
                        "Player" + numberOfConnections, this);
        service.submit(clientConnectionHandler);
    }

    public void launchGame(List<ClientConnectionHandler> playersList) {
        System.out.println("Game starting.");
        Game game = new Game(playersList, new OriginalDeck().getDeck());
        Thread thread = new Thread(game);
        games.submit(thread);
    }

    void addClient(ClientConnectionHandler clientConnectionHandler) {
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

}


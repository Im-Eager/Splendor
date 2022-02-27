/**
 * @(#)ClientConnectionHandler.java        1.0 28/02/2022
 *
 * Copyright© MindSwap Academy - Diogo Noronha, Luis Faria, Ricardo Paiva, Tiago Miranda
 * All rights reserved.
 *
 * This software was produced to become our first group project.
 */

package academy.mindswap.server;

import academy.mindswap.game.Game;
import academy.mindswap.game.Player;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientConnectionHandler implements Runnable {

    private final String name;
    private final Socket clientSocket;
    private BufferedWriter out;
    private String message;
    private Player player;
    private Server server;
    private Game game;
    private boolean hasPlayerGivenCommand;

    public ClientConnectionHandler(Socket clientSocket, String name, Server server) throws IOException {
        this.server = server;
        this.clientSocket = clientSocket;
        this.name = name;
        this.out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        this.hasPlayerGivenCommand = true;
    }

    @Override
    public void run() {

        server.addClient(this);
        try {
            Scanner in = new Scanner(clientSocket.getInputStream());
            while (in.hasNext()) {
                message = in.nextLine();
                //hasPlayerGivenCommand = true;
                if (message.equals("")) {
                    continue;
                }
                server.broadcast(name, message);
                if (this.player.isPlaying() && isCommand(message)){
                    game.setCommand(message);
                    this.hasPlayerGivenCommand = true;
                }
            }
        } catch (IOException e) {
            System.err.println("Messages.CLIENT_ERROR + e.getMessage()");
        } finally {
            server.removeClient(this);
        }
    }

    private boolean isCommand(String message) {
        return message.startsWith("/");
    }

/*    public void dealWithCommand(String message) *//*throws IOException *//* {
        if (isCommand(message)) {
            String description = message.toUpperCase().split(" ")[0];
            Command command = Command.getCommandFromDescription(description);

            if (command == null) {
                try {
                    out.write(Messages.IMPOSSIBLE_MOVE);
                    out.newLine();
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }

            command.getHandler().execute(server, this); // CHECK IF SENDING VIA CORRECT SERVER
            player.setPlaying(false);
            clientSocket.notifyAll();
        }
    }*/

    public void send(String message) {
        //dealWithCommand(message);
        try {
            out.write(message);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            server.removeClient(this);
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

    public void setHasPlayerGivenCommand(boolean setBoolean){
        this.hasPlayerGivenCommand = false;
    }

    public boolean getHasPlayerGivenCommand(){
        return this.hasPlayerGivenCommand;
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

    public void setGame(Game game){
        this.game = game;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

}

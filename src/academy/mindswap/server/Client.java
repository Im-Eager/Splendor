package academy.mindswap.server;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.start("localhost", 8080);
        } catch (IOException e) {
            System.out.println("Connection closed...");
        }
    }

    private void start(String host, int port) throws IOException {
        System.out.println("Connection established.");

        Socket socket = new Socket(host, port);

        BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));

        String lineReceived;
        String lineSent;

        while (true){
            if ((lineReceived = socketIn.readLine()) != null){
                System.out.println(lineReceived);
            }

            socketOut.write(consoleIn.read());
            socketOut.newLine();
            socketOut.flush();
        }
    }
}
package academy.mindswap.commands;

import academy.mindswap.server.Server;

public interface CommandHandler {

//    public interface CommandHandler {
//        void execute(Server server, Server.ClientConnectionHandler clientConnectionHandler);
//    }

        void execute(Server server);
    }
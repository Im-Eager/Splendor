package academy.mindswap.commands;

import academy.mindswap.server.ClientConnectionHandler;
import academy.mindswap.server.Server;

public interface CommandHandler {

        void execute(Server server, ClientConnectionHandler clientConnectionHandler);
    }

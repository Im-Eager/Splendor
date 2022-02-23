package academy.mindswap.commands;

public enum Command {
    BUY("B", new BuyHandler()),
    GRAB("G", new GrabHandler()),
    RESERVE("R", new ReserveHandler());

    private String description;
    private CommandHandler handler;

    Command(String description, CommandHandler handler) {
        this.description = description;
        this.handler = handler;
    }

    public static Command getCommandFromDescription(String description) {
        for (Command command : values()) {
            if (description.toUpperCase().equals(command.description)) {
                return command;
            }
        }
        return null;
    }

    public CommandHandler getHandler() {
        return handler;
    }
}

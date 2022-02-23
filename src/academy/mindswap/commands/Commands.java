package academy.mindswap.commands;

public enum Commands {
    BUY("B"),
    GRAB("G"),
    RESERVE("R");

    private String description;

    Commands(String description) {
        this.description = description;
    }
}

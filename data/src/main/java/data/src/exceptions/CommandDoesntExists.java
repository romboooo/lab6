package data.src.exceptions;

public class CommandDoesntExists extends Exception {
    public CommandDoesntExists(String input){
        super("command " + input + " doesn't exists");
    }
}

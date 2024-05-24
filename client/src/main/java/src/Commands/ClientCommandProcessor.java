package src.Commands;

import data.src.exceptions.CommandDoesntExists;
import src.Client;

import java.util.HashMap;

public class ClientCommandProcessor {

    public static HashMap<String, AbstractClientCommand> commands = new HashMap<>();
    private final Client client;
    public ClientCommandProcessor(Client client){
        this.client = client;
        commands.put("add", new AddCommandClient(client));
        commands.put("exit",new ExitCommandClient());
        commands.put("add_if_min",new AddIfMinCommandClient(this,client));
        commands.put("add_if_max", new AddIfMaxCommandClient(this,client));

    }
    public HashMap<String, AbstractClientCommand> getCommand(){
        return commands;
    }
    public static void executeCommand(String input) throws CommandDoesntExists {
        String[] commandSplit = input.split(" ", 2);
        String commandName = commandSplit[0];
        if (!ISCommandContains(commandName)){
            throw new CommandDoesntExists(commandName);
        }
        if (commandSplit.length < 2) {
            commands.get(commandName).ExecuteCommand("");
            return;
        }
        commands.get(commandName).ExecuteCommand(commandSplit[1]);

    }

    public static Boolean ISCommandContains(String input){
        return commands.containsKey(input);
    }
}

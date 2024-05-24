package commands;

import commands.absctractCommands.Command;
import data.src.exceptions.CommandDoesntExists;

import java.io.IOException;

public class PingCommandServer extends Command {
    @Override
    public String ExecuteCommand(String... args) throws IOException, CommandDoesntExists {

        return "connection successful";
    }

    @Override
    public String getCommandArguments() {
        return "";
    }
}

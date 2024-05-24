package commands.absctractCommands;

import Interfaces.Executable;
import data.src.exceptions.CommandDoesntExists;


import java.io.IOException;

public abstract class Command implements Executable {
    private String name;
    public String getCommandName(){
        return this.name;
    }


    public abstract String ExecuteCommand(String... args) throws IOException, CommandDoesntExists;
    public abstract String getCommandArguments();

}

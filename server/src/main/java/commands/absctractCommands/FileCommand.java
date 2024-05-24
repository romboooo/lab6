package commands.absctractCommands;

import Interfaces.Executable;

import java.io.IOException;

public abstract class FileCommand extends Command implements Executable {

    private String name;
    public String getCommandName(){
        return this.name;
    }
    @Override
    public abstract String ExecuteCommand(String... args) throws IOException;
}

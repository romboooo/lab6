package commands.absctractCommands;

import Interfaces.Executable;

public abstract class ConsoleCommand extends Command implements Executable {

    private String name;
    public String getCommandName(){
        return this.name;
    }


    @Override
    public String ExecuteCommand(String... args) {
        return null;
    }
}

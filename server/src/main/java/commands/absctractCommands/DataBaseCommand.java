package commands.absctractCommands;

import Interfaces.Executable;


public abstract class DataBaseCommand extends Command implements Executable {
    @Override
    public abstract String ExecuteCommand(String... args);
}

package commands.absctractCommands;


import Interfaces.Executable;


//@NoArgsConstructor(force = true)
public abstract class ProcessorsCommand extends Command implements Executable {


    @Override
    public abstract String ExecuteCommand(String... args);
}

package commands.dataBaseCommands;
import Interfaces.Executable;
import commands.absctractCommands.DataBaseCommand;
import data.src.Proccessors.DataBaseProcessor;
import data.src.defaultClasses.Ticket;
import lombok.EqualsAndHashCode;

import java.util.HashMap;

@EqualsAndHashCode
public class RemoveFirstCommand extends DataBaseCommand implements Executable {
    final DataBaseProcessor dataBaseProcessor;

    public RemoveFirstCommand(DataBaseProcessor dataBaseProcessor) {
        this.dataBaseProcessor = dataBaseProcessor;
    }

    private String name = "remove_first_command";
    public String getCommandName(){
        return this.name;
    }

    @Override
    public String ExecuteCommand(String... args)  {
        HashMap<Ticket, Boolean> undoHashmap = new HashMap<>();
        undoHashmap.put(dataBaseProcessor.tickets.getFirst(),false);
        dataBaseProcessor.undoStack.push(undoHashmap);
        dataBaseProcessor.tickets.removeFirst();

        return "done!";
    }

    @Override
    public String getCommandArguments() {
        return "";
    }
}

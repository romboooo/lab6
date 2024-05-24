package commands.dataBaseCommands;
import Interfaces.Executable;
import commands.absctractCommands.DataBaseCommand;
import data.src.Proccessors.DataBaseProcessor;
import data.src.defaultClasses.Ticket;
import lombok.EqualsAndHashCode;

import java.util.HashMap;

@EqualsAndHashCode
public class ClearCommand extends DataBaseCommand implements Executable {
    final DataBaseProcessor dataBaseProcessor;


    private String name = "clear";
    public String getCommandName(){
        return this.name;
    }


    public ClearCommand(DataBaseProcessor dataBaseProcessor) {
        this.dataBaseProcessor = dataBaseProcessor;
    }

    @Override
    public String ExecuteCommand(String... args)  {
        HashMap<Ticket, Boolean> undoHashmap = new HashMap<>();
        for(Ticket ticket : dataBaseProcessor.tickets){
            undoHashmap.put(ticket,false);
            dataBaseProcessor.undoStack.push(undoHashmap);
        }

        dataBaseProcessor.tickets.clear();

        return "collection is clear!";
    }

    @Override
    public String getCommandArguments() {
        return "";
    }
}

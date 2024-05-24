package commands.dataBaseCommands;

import Interfaces.Executable;
import commands.absctractCommands.DataBaseCommand;
import data.src.Proccessors.DataBaseProcessor;
import lombok.EqualsAndHashCode;
import data.src.defaultClasses.Ticket;

import java.util.HashMap;

@EqualsAndHashCode
public class RemoveByIdCommand extends DataBaseCommand implements Executable {

    final DataBaseProcessor dataBaseProcessor;

    public RemoveByIdCommand(DataBaseProcessor dataBaseProcessor) {
        this.dataBaseProcessor = dataBaseProcessor;
    }

    private String name = "remove_by_id";
    public String getCommandName(){
        return this.name;
    }

    @Override
    public String ExecuteCommand(String... args) {
        try {
            if (args == null) {
                return "command expects argument. please try it one more time";
            }

            int id = Integer.valueOf(args[0]);


            for (Ticket ticket : dataBaseProcessor.tickets) {
                if (id == ticket.getId()) {
                    dataBaseProcessor.tickets.remove(ticket);
                    HashMap<Ticket, Boolean> undoHashmap = new HashMap<>();
                    undoHashmap.put(ticket,false);
                    dataBaseProcessor.undoStack.push(undoHashmap);
                    return "done!";
                }
            }

            return "there isn't ticket with id: " + id + "  enter \"show\" for view a tickets info";
        } catch (ArrayIndexOutOfBoundsException e) {
            return "argument entered incorrectly. please try it one more time";
        } catch (NumberFormatException e){
            return "argument should be a number. please try it one more time";
        }
    }

    @Override
    public String getCommandArguments() {
        return " id";
    }
}

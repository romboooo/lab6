package commands.dataBaseCommands;
import Interfaces.Executable;
import commands.absctractCommands.DataBaseCommand;

import data.src.Proccessors.DataBaseProcessor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class InfoCommand extends DataBaseCommand implements Executable {

    private String name = "info";
    public String getCommandName(){
        return this.name;
    }

    final DataBaseProcessor dataBase;

    public InfoCommand(DataBaseProcessor dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public String ExecuteCommand(String... args) {

        String message = "collection type: " + dataBase.tickets.getClass()
                + "\n" +
                "initialization date: " + dataBase.tickets.getFirst().getCreationDate()
                + "\n" +
                "amount of elements: " + dataBase.tickets.size();

        return message;
    }

    @Override
    public String getCommandArguments() {
        return "";
    }
}

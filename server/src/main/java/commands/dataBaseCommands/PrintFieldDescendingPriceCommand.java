package commands.dataBaseCommands;
import Interfaces.Executable;
import commands.absctractCommands.DataBaseCommand;
import data.src.Proccessors.DataBaseProcessor;
import lombok.EqualsAndHashCode;
import data.src.defaultClasses.Ticket;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode
public class PrintFieldDescendingPriceCommand extends DataBaseCommand implements Executable {
    final DataBaseProcessor dataBase;

    public PrintFieldDescendingPriceCommand(DataBaseProcessor dataBase) {
        this.dataBase = dataBase;
    }

    private String name = "print_field_descending_price";
    public String getCommandName(){
        return this.name;
    }

    @Override
    public String ExecuteCommand(String... args)  {
        List<Long> price = new ArrayList();

        for (Ticket ticket : dataBase.tickets){
            price.add(ticket.getPrice());
        }
        return price.stream()
                .sorted(Comparator.reverseOrder())
                .map(String::valueOf)
                .map(line ->  line + "\n")
                .collect(Collectors.joining());
    }

    @Override
    public String getCommandArguments() {
        return "";
    }
}

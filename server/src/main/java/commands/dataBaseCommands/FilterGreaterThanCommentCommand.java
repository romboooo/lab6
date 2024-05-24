package commands.dataBaseCommands;
import Interfaces.Executable;
import commands.absctractCommands.DataBaseCommand;
import data.src.Proccessors.DataBaseProcessor;
import lombok.EqualsAndHashCode;
import data.src.defaultClasses.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode
public class FilterGreaterThanCommentCommand extends DataBaseCommand implements Executable {
    final DataBaseProcessor dataBase;

    private String name = "filter_greater_than_comment";
    public String getCommandName(){
        return this.name;
    }
    public FilterGreaterThanCommentCommand(DataBaseProcessor dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public String ExecuteCommand(String... args) {
       try {

           if (args == null) {
               return "command expects argument. please try it one more time";
           }
           int lenght = args[0].length();

           List<Ticket> ticketList = new ArrayList();

           for (Ticket ticket : dataBase.tickets) {
               if (ticket.getComment().length() > lenght) {
                   ticketList.add(ticket);
               }
           }

           return ticketList.stream()
                   .map(String::valueOf)
                   .map(line -> line + "\n")
                   .collect(Collectors.joining());
       }catch (ArrayIndexOutOfBoundsException e){
           return "argument entered incorrectly. please try it one more time";
       }
    }

    @Override
    public String getCommandArguments() {
        return " {comment}";
    }
}

package commands.dataBaseCommands;

import Interfaces.Executable;
import commands.absctractCommands.DataBaseCommand;
import data.src.Proccessors.DataBaseProcessor;
import data.src.defaultClasses.Ticket;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;

import java.util.Comparator;
import java.util.stream.Collectors;

@EqualsAndHashCode
public class ShowCommand extends DataBaseCommand implements Executable {

    final DataBaseProcessor dataBase;

    public ShowCommand(DataBaseProcessor dataBase) {
        this.dataBase = dataBase;
    }

    private String name = "show";
    public String getCommandName(){
        return this.name;
    }

    @SneakyThrows
    @Override
    public String ExecuteCommand(String... args)  {

        return "if you don't see your ticket, check data in file.csv"
                + "\n" +
                dataBase.tickets.stream().sorted(Comparator.comparingInt(Ticket::getId)).map(ticket ->
            ticket + "\n"
        ).collect(Collectors.joining());

    }

    @Override
    public String getCommandArguments() {
        return "";
    }
}

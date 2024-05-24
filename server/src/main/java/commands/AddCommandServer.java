package commands;

import Processors.CommandProcessor;
import Processors.FileProcessor;
import commands.absctractCommands.Command;
import data.src.Proccessors.DataBaseProcessor;
import data.src.defaultClasses.*;
import data.src.exceptions.CommandDoesntExists;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

public class AddCommandServer extends Command {

    final DataBaseProcessor dataBaseProcessor;
    final CommandProcessor commandProcessor;
    private FileProcessor.Parser parser;

    public AddCommandServer(DataBaseProcessor dataBaseProcessor, CommandProcessor commandProcessor,FileProcessor.Parser parser) {
        this.dataBaseProcessor = dataBaseProcessor;
        this.commandProcessor = commandProcessor;
        this.parser = parser;

    }
    @Override
    public String ExecuteCommand(String... args) throws IOException, CommandDoesntExists {
        String name = args[0];
        Float x = Float.parseFloat(args[1]);
        int intx = Integer.parseInt(args[1]);
        Float y = Float.parseFloat(args[2]);
        Long longY = Long.parseLong(args[2]);
        Long price = Long.parseLong(args[3]);
        int discount = Integer.parseInt(args[4]);
        String comment = args[5];
        TicketType type =  TicketType.valueOf(args[6]);
        int weight = Integer.parseInt(args[7]);
        String passportID = args[8];
        String locationName = args[9];

        Ticket ticket = dataBaseProcessor.createNewTicket(
                dataBaseProcessor.createID(), name,
                new Coordinates(x, y),
                Date.from(Instant.now()),
                price, discount, comment, type,
                new Person(
                        java.time.LocalDateTime.now(),
                        weight, passportID,
                        new Location(intx, longY, locationName)
                            )
        );

        dataBaseProcessor.tickets.add(ticket);

        parser.writeToFile(dataBaseProcessor.tickets.stream().collect(Collectors.toList()));

        return "ticket was created, don't forget to save it!" + "\n" +
                "Type \"help\" for display available commands:";
    }

    @Override
    public String getCommandArguments() {
        return "";
    }
}

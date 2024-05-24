package Processors;

import commands.AddCommandServer;
import commands.PingCommandServer;
import commands.absctractCommands.Command;
import commands.dataBaseCommands.*;
import commands.processorsCommands.HelpCommand;
import data.src.Proccessors.DataBaseProcessor;
import data.src.Proccessors.Processor;
import data.src.exceptions.CommandDoesntExists;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.HashMap;

@Getter
public class CommandProcessor extends Processor {

    private HashMap<String, Command> commands = new HashMap<>();

    final DataBaseProcessor dataBaseProcessor;
    final FileProcessor.Parser parser;


    public CommandProcessor(DataBaseProcessor dataBaseProcessor, FileProcessor.Parser parser){
        this.parser = parser;
        this.dataBaseProcessor = dataBaseProcessor;
        commands.put("help", new HelpCommand(this));
        commands.put("add", new AddCommandServer(dataBaseProcessor, this,parser));
        commands.put("clear", new ClearCommand(dataBaseProcessor));
        commands.put("count_greater_than_price", new CountGreaterThanPriceCommand(dataBaseProcessor));
        commands.put("filter_greater_than_comment", new FilterGreaterThanCommentCommand(dataBaseProcessor));
        commands.put("info", new InfoCommand(dataBaseProcessor));
        commands.put("print_field_descending_price", new PrintFieldDescendingPriceCommand(dataBaseProcessor));
        commands.put("remove_by_id", new RemoveByIdCommand(dataBaseProcessor));
        commands.put("remove_first", new RemoveFirstCommand(dataBaseProcessor));
        commands.put("show", new ShowCommand(dataBaseProcessor));
        commands.put("update_id", new UpdateIdCommand(dataBaseProcessor));
        commands.put("ping",new PingCommandServer());
    }


    @SneakyThrows
    public String executeCommand(String input){
        String[] commandSplit = input.strip().split(" ", 2);
        String commandName = commandSplit[0];
        String[] commandArguments = null;

        if (commandSplit.length == 2) {
            commandArguments = commandSplit[1].split(" ");
        }


        if (commands.containsKey(commandName)) {
            return commands.get(commandName).ExecuteCommand(commandArguments);
        } else {
            throw new CommandDoesntExists(commandName);
        }


    }
}

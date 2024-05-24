package commands.processorsCommands;

import Processors.CommandProcessor;
import commands.absctractCommands.Command;
import commands.absctractCommands.ProcessorsCommand;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@EqualsAndHashCode
public class HelpCommand extends ProcessorsCommand {
    CommandProcessor processor;
    public HelpCommand(CommandProcessor processor) {
        this.processor = processor;

    }

    private String name = "help";

    public String getCommandName(){
        return this.name;
    }



    @Override
    public String ExecuteCommand(String... args)  {
        List<String> AllCommands= new ArrayList<>();
        for (Map.Entry<String, Command> commandName : processor.getCommands().entrySet()){
            AllCommands.add(commandName.getKey() +commandName.getValue().getCommandArguments() + "\n");


        }
        return AllCommands.stream().collect(Collectors.joining());
    }

    @Override
    public String getCommandArguments() {
        return "";
    }
}

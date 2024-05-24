package commands.fileCommands;
import Interfaces.Executable;
import commands.absctractCommands.FileCommand;
import Processors.FileProcessor;
import data.src.Proccessors.DataBaseProcessor;
import lombok.EqualsAndHashCode;

import java.io.IOException;
import java.util.stream.Collectors;


@EqualsAndHashCode
public class SaveCommand extends FileCommand implements Executable {
    final DataBaseProcessor dataBase;
    final FileProcessor.Parser parser;

    public SaveCommand(DataBaseProcessor dataBase, FileProcessor.Parser parser) {
        this.dataBase = dataBase;
        this.parser = parser;
    }

    @Override
    public String ExecuteCommand(String... args) throws IOException {
        parser.writeToFile(dataBase.tickets.stream().collect(Collectors.toList()));
        return "done!";
    }

    @Override
    public String getCommandArguments() {
        return "";
    }
}

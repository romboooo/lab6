import Processors.CommandProcessor;
import Processors.FileProcessor;
import data.src.Proccessors.DataBaseProcessor;
import lombok.SneakyThrows;

public class ServerMain {

    @SneakyThrows
    public static void main(String[] args) {
        FileProcessor fileProcessor = new FileProcessor();
        FileProcessor.Parser parser = new FileProcessor.Parser();
        DataBaseProcessor dataBaseProcessor = new DataBaseProcessor();
        CommandProcessor commandProcessor = new CommandProcessor(dataBaseProcessor,parser);
        Server server = new Server(commandProcessor);

        dataBaseProcessor.tickets.addAll(parser.readFromFile());
        server.run();
        server.start();
    }
}


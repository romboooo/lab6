package src;

import src.Commands.ClientCommandProcessor;
import src.Commands.ExecuteScriptCommandClient;
import lombok.SneakyThrows;
import src.Commands.ConnectionChecker;
import src.validators.Validator;

public class ClientMain {

    @SneakyThrows
    public static void main(String[] args) {
        Client client = new Client();
        Validator validator = new Validator();
        ClientCommandProcessor clientCommandProcessor = new ClientCommandProcessor(client);
        ConsoleProcessor consoleProcessor = new ConsoleProcessor(validator,client,clientCommandProcessor);
        clientCommandProcessor.getCommand().put("execute_script", new ExecuteScriptCommandClient(consoleProcessor));
        ConnectionChecker connectionChecker = new ConnectionChecker(client,consoleProcessor);

        if(connectionChecker.connect()) {
            consoleProcessor.consoleExecute();
        }

    }

}

package src;

import data.src.Proccessors.Processor;
import src.Commands.ClientCommandProcessor;
import data.src.communicationDataStructs.Request;
import lombok.Data;
import lombok.SneakyThrows;
import src.validators.Validator;

import java.util.NoSuchElementException;
import java.util.Scanner;

@Data
public class ConsoleProcessor extends Processor {


    private final Client client;
    private final Validator validator;
    private final ClientCommandProcessor clientCommandProcessor;

    public ConsoleProcessor(Validator validator, Client client, ClientCommandProcessor clientCommandProcessor) {
        this.validator = validator;
        this.client = client;
        this.clientCommandProcessor = clientCommandProcessor;
    }

    public void consoleExecute() {

        System.out.println("Type \"help\" for display available commands:");
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                String in = scanner.nextLine().strip();
                execute(in);
            }
        } catch (NoSuchElementException e) {
            System.err.println("консоль остановлена");
        }
    }

    @SneakyThrows
    public void execute(String in) {
        String[] input = in.split(" ");
        String commandName = input[0];
        String firstArg = "";
        String secondArg = "";
        if (!validator.isValid(in)) {
            System.err.println("you wrote command incorrectly");
            return;
        }
        if (clientCommandProcessor.ISCommandContains(commandName)) {
            clientCommandProcessor.executeCommand(in);
            return;
        }

        if (input.length >= 2)
            firstArg = input[1];
        if (input.length == 3)
            secondArg = input[2];
        Request request = new Request(input[0], new String[]{firstArg, secondArg});
//        request.getCommandArgs();

        System.out.println(client.sendRequest(request));
    }

}

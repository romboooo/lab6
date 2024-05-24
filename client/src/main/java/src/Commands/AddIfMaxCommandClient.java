package src.Commands;

import data.src.communicationDataStructs.Request;
import data.src.exceptions.CommandDoesntExists;
import src.Client;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AddIfMaxCommandClient extends AbstractClientCommand{
    private String name = "add_if_max";
    private final ClientCommandProcessor clientCommandProcessor;
    private final Client client;

    public AddIfMaxCommandClient(ClientCommandProcessor clientCommandProcessor, Client client){

        this.clientCommandProcessor = clientCommandProcessor;
        this.client = client;
    }

    public String getCommandName() {
        return this.name;
    }


    @Override
    public void ExecuteCommand(String in) {


        try {
            Long input = Long.valueOf(in);
            Request request = new Request("print_field_descending_price", new String[]{String.valueOf(input), ""}) ;

            String price = client.sendRequest(request);

            List<Long> prices = List.of(price.split("\n")).stream().map(Long::parseLong).collect(Collectors.toList());


            if (input <= Collections.max(prices)) {
                System.out.println("The price you entered is not the minimum element of the ticket collection, enter \"print_field_descending_price\" for view a price info ");
                return;
            }
            try {
                clientCommandProcessor.executeCommand("add");
            } catch (CommandDoesntExists e) {
                System.err.println(e.getMessage());
            }
        } catch (NumberFormatException e){
            System.err.println(e.getMessage());
        }
    }

}

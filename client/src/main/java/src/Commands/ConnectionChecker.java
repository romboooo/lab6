package src.Commands;

import data.src.communicationDataStructs.Request;
import src.Client;
import src.ConsoleProcessor;

import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.TimerTask;

public class ConnectionChecker {

    private final Client client;
    private final ConsoleProcessor consoleProcessor;

    public ConnectionChecker(Client client, ConsoleProcessor consoleProcessor) {
        this.client = client;
        this.consoleProcessor = consoleProcessor;
    }

    public Boolean connect() {
        System.out.println("connection to server... ");

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("error with connection to server, plz try it later");
                System.exit(0);

            }
        }, 5000L);
        Request pingRequest = new Request("ping", new String[]{"",""});
        String response = client.sendRequest(pingRequest);
        if(!response.isBlank()){
            timer.cancel();
            System.out.println("connection successful");
            return true;
        }else {
            System.out.println("connection lost");
            return false;

        }
    }
}

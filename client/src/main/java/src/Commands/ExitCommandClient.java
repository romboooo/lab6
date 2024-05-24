package src.Commands;


public class ExitCommandClient extends AbstractClientCommand{

    private String name = "exit";

    public void ExecuteCommand(String args) {
        System.out.println("session is ended, goodbye :) ");
        System.exit(0);
    }


}

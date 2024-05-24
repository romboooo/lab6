package src.Commands;

import data.src.communicationDataStructs.Request;
import data.src.defaultClasses.Ticket;
import src.Client;

import java.util.HashMap;
import java.util.Scanner;

public class AddCommandClient extends AbstractClientCommand{
    HashMap<String,String> ticketFields = new HashMap<>();
    private final Client client;

    public AddCommandClient(Client client){
        this.client = client;
    }

    @Override
    public void ExecuteCommand(String args){
        Scanner scanner = new Scanner(System.in);
        enterData(scanner);

        String[] ticketData  = new String[] {
                ticketFields.get("name"),
                ticketFields.get("x"),
                ticketFields.get("y"),
                ticketFields.get("price"),
                ticketFields.get("discount"),
                ticketFields.get("comment"),
                ticketFields.get("type"),
                ticketFields.get("weight"),
                ticketFields.get("passportID"),
                ticketFields.get("locationName")
        };
        Object objectArgs = ticketData;
        Request request1 = new Request("add",new String[]{"",""},objectArgs);
        System.out.println(client.sendRequest(request1));
        ticketFields.clear();
    }
    private void enterData(Scanner scanner){

        enterName(scanner);
        enterX(scanner);
        enterY(scanner);
        enterPrice(scanner);
        enterDiscount(scanner);
        enterComment(scanner);
        enterType(scanner);
        enterWeight(scanner);
        enterPassportID(scanner);
        enterLocationName(scanner);
    }

    public void enterName(Scanner scanner){
        System.out.println("enter name:");
        String scannerName = scanner.nextLine();


        if (Ticket.Checker.checkName(scannerName)){
            String name = scannerName;
            System.out.println("done!");
            ticketFields.put("name", name);
            printEnteredValues();
        } else {
            System.out.println("name must not be empty");
            enterName(scanner);
        }
    }

    public void enterX(Scanner scanner){
        System.out.println("enter x");
        String scannerX = scanner.nextLine().strip();

        try {
            if (Ticket.Checker.checkX(Float.valueOf(scannerX))) {
                String x = scannerX;
                ticketFields.put("x", x);
                System.out.println("done!");
                printEnteredValues();
            } else {
                System.out.println("x must be a number and not null");
                enterX(scanner);
            }
        } catch(NumberFormatException e){
            System.out.println("x must be a number");
            System.out.println("also it must be bigger than -1001 and less than 1001");
            enterX(scanner);
        }
    }
    public void enterY(Scanner scanner){
        System.out.println("enter y");
        String scannerY = scanner.nextLine().strip();

        try {
            if (Ticket.Checker.checkY(Float.valueOf(scannerY))) {
                String y = scannerY;
                ticketFields.put("y", y);
                System.out.println("done!");
                printEnteredValues();
            } else {
                System.out.println("x coordinate must not be empty");
                enterY(scanner);
            }
        } catch (NumberFormatException e){
            System.out.println("y must be a number ");
            System.out.println("also it must be bigger than -1001 and less than 1001");
            enterY(scanner);
        }
    }
    public void enterPrice(Scanner scanner){
        System.out.println("enter price");
        String scannerPrice = scanner.nextLine().strip();

        try {
            if (Ticket.Checker.checkPrice(Long.valueOf(scannerPrice))) {
                Long price = Long.valueOf(scannerPrice);
                ticketFields.put("price", price.toString());
                System.out.println("done!");
                printEnteredValues();
            } else {
                System.out.println("price must be not empty and bigger than zero");
                enterPrice(scanner);
            }
        } catch (NumberFormatException e){
            System.out.println("price must be a number");
            enterPrice(scanner);
        }
    }

    public void enterDiscount(Scanner scanner){
        System.out.println("enter discount (it must be bigger than 0 and less than 101)");
        String scannerDiscount = scanner.nextLine().strip();

        try {
            if(Ticket.Checker.checkDiscount(Integer.parseInt(scannerDiscount))){
                int discount = Integer.parseInt(scannerDiscount);
                ticketFields.put("discount", String.valueOf(discount));
                System.out.println("done!");
                printEnteredValues();
            }
            else{
                System.out.println("discount must be bigger than 0 and less than 101!");
                enterDiscount(scanner);
            }
        } catch (NumberFormatException e){
            System.out.println("discount must be a number");
            System.out.println("also it must be bigger than 0 and less than 101");
            enterDiscount(scanner);
        }
    }


    public void enterComment(Scanner scanner){
        System.out.println("enter comment");
        String scannerComment = scanner.nextLine();
        if(Ticket.Checker.checkComment(scannerComment)){
            String comment = scannerComment;
            ticketFields.put("comment",comment);
            System.out.println("done!");
            printEnteredValues();
        }else {
            System.out.println("comment must not be empty");
            enterComment(scanner);
        }
    }

    public void enterType(Scanner scanner){

        System.out.println("enter type (you can write it in lower case)");
        System.out.println("VIP / USUAL / BUDGETARY / CHEAP");

        String scannerType = scanner.nextLine().strip().toUpperCase();
        if((scannerType.equals("VIP")) || (scannerType.equals("USUAL")) || (scannerType.equals("BUDGETARY")) || (scannerType.equals("CHEAP"))){
            String type = scannerType;
            ticketFields.put("type",type);
            System.out.println("done!");
            printEnteredValues();
        } else{
            System.out.println("enter valid type VIP / USUAL / BUDGETARY / CHEAP");
            enterType(scanner);
        }
    }

    public void enterWeight(Scanner scanner){
        System.out.println("enter weight of person");

        String scannerWeight = scanner.nextLine().strip();
        try{
            if (Integer.valueOf(scannerWeight) > 0){
                int weight = Integer.valueOf(scannerWeight);
                ticketFields.put("weight", String.valueOf(weight));
                System.out.println("done!");
                printEnteredValues();
            } else{
                System.out.println("weight must be bigger than 0");
                enterWeight(scanner);
            }
        } catch(NumberFormatException e){
            System.out.println("weight must be a number");
            enterWeight(scanner);
        }
    }


    public void enterPassportID(Scanner scanner){
        System.out.println("enter passportID");
        String scannerPassportID = scanner.nextLine().strip();

        if (scannerPassportID.length() >= 4){
            String passportID = scannerPassportID;
            ticketFields.put("passportID", passportID);
            System.out.println("done!");
            printEnteredValues();
        } else{
            System.out.println("passportID's lenght must be >= 4");
            enterPassportID(scanner);
        }
    }
    public void enterLocationName(Scanner scanner){
        System.out.println("enter location name");
        String scannerLocationName = scanner.nextLine();
        if(!scannerLocationName.isBlank()){
            String locationName = scannerLocationName;
            ticketFields.put("locationName",locationName);
            System.out.println("done!");
            printEnteredValues();
        } else{
            System.out.println("location name must not be empty");
            enterLocationName(scanner);
        }
    }

    public void printEnteredValues() {
        ticketFields.entrySet().forEach((entry) -> {
            System.out.println(entry.getKey()+": "+entry.getValue());
        });
    }




    public String getCommandArguments() {
        return null;
    }
}

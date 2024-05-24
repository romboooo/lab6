package commands.dataBaseCommands;
import Interfaces.Executable;
import commands.absctractCommands.DataBaseCommand;
import data.src.Proccessors.DataBaseProcessor;
import lombok.EqualsAndHashCode;
import data.src.defaultClasses.Ticket;

@EqualsAndHashCode
public class CountGreaterThanPriceCommand extends DataBaseCommand implements Executable {
    final DataBaseProcessor dataBase;

    public CountGreaterThanPriceCommand(DataBaseProcessor dataBase) {
        this.dataBase = dataBase;
    }

    private String name = "count_greater_than_price";
    public String getCommandName(){
        return this.name;
    }

    @Override
    public String ExecuteCommand(String... args) {

        try {
            if (args == null) {
                return "command expects argument. please try it one more time";
            }

            int price = Integer.parseInt(args[0]);
            int count = 0;

            for (Ticket ticket : dataBase.tickets) {
                if (ticket.getPrice() > price) {
                    count += 1;
                }
            }
            return String.valueOf(count);
        } catch (ArrayIndexOutOfBoundsException e) {
            return "argument entered incorrectly. please try it one more time";

        } catch (NumberFormatException e) {
            return "argument \"price\" should be a number. '" + args[0] + "' is not a number. Try it one more time";
        }
    }

    @Override
    public String getCommandArguments() {
        return " {price}";
    }
}

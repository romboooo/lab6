package src.validators;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validator {
    private final Map<String, CommandData> commandDataMap = new HashMap<String, CommandData>();
    public Validator(){
        commandDataMap.put("add",addCommandData);
        commandDataMap.put("add_if_min", addIfMinCommandData);
        commandDataMap.put("add_if_max", addIfMaxCommandData);
        commandDataMap.put("clear", clearCommandData);
        commandDataMap.put("count_greater_than_price", countGreaterThanThePriceCommandData);
        commandDataMap.put("filter_greater_than_comment", filterGreaterThanCommentCommandData);
        commandDataMap.put("info", infoCommandData);
        commandDataMap.put("print_field_descending_price", printFieldDescendingPriceCommandData);
        commandDataMap.put("remove_by_id",removeByIDCommandData);
        commandDataMap.put("remove_first", removeFirstCommandData);
        commandDataMap.put("show",showCommandData);
        commandDataMap.put("update_id", updateIDCommandData);
        commandDataMap.put("execute_script", executeScriptCommandData);
        commandDataMap.put("help",helpCommandData);
        commandDataMap.put("exit", exitCommandData);
    }

    public boolean isValid(String input) {

        input = input.strip();
        String[] in = input.split(" ", 2);
        String commandName = in[0];
        if(!commandNames.contains(commandName)){
            return false;
        }

        CommandData args = commandDataMap.get(commandName);

        if(in.length == 1){
            return true;
        }
        String[] argsString = in[1].split(" ", args.commandArgs.arg.length);
//        if (commandName.equals("execute_script")){
//            args.getCommandArgs().getArg().toString();
//            return true;
//        }


        if (args.getCommandArgs().arg.length != argsString.length) {
                return false;
        }




        for(int i = 0; i < argsString.length; i++){
            if (Long.class.equals(args.getCommandArgs().getArg()[i])) {
                try {
                    Long.parseLong(argsString[i]);
                } catch (NumberFormatException e) {
                    System.err.println(e.getMessage());
                    return false;
                }
            } else if (Integer.class.equals(args.getCommandArgs().getArg()[i])) {
                try {
                    Integer.parseInt(argsString[i]);
                } catch (NumberFormatException e) {
                    System.err.println(e.getMessage());
                    return false;
                }
            } else if (String.class.equals(args.getCommandArgs().getArg()[i])) {
                if (argsString[i].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    CommandArg addCommandArg = new CommandArg(new Class[]{});
    CommandData addCommandData = new CommandData("add", addCommandArg);
    CommandArg addIfMinCommandArg = new CommandArg(new Class[]{Long.class});
    CommandData addIfMinCommandData = new CommandData("add_if_min", addIfMinCommandArg);
    CommandArg addIfMaxCommandArg = new CommandArg(new Class[]{Long.class});
    CommandData addIfMaxCommandData = new CommandData("add_if_max", addIfMaxCommandArg);
    CommandArg clearCommandArg = new CommandArg(new Class[]{});
    CommandData clearCommandData = new CommandData("clear", clearCommandArg);
    CommandArg countGreaterThanThePriceCommandArg = new CommandArg(new Class[]{Integer.class});
    CommandData countGreaterThanThePriceCommandData = new CommandData("count_greater_than_the_price",
                                                                            countGreaterThanThePriceCommandArg);
    CommandArg filterGreaterThanCommentCommandArg = new CommandArg(new Class[]{String.class});
    CommandData filterGreaterThanCommentCommandData = new CommandData("filter_greater_than_comment",
                                                                             filterGreaterThanCommentCommandArg);
    CommandArg infoCommandArg = new CommandArg(new Class[]{});
    CommandData infoCommandData = new CommandData("info", infoCommandArg);
    CommandArg printFieldDescendingPriceCommandArg = new CommandArg(new Class[]{Integer.class});
    CommandData printFieldDescendingPriceCommandData = new CommandData("print_field_descending_price",
                                                                                printFieldDescendingPriceCommandArg);
    CommandArg removeByIDCommandArg = new CommandArg(new Class[]{Integer.class});
    CommandData removeByIDCommandData = new CommandData("remove_by_id", removeByIDCommandArg);
    CommandArg removeFirstCommandArg = new CommandArg(new Class[]{});
    CommandData removeFirstCommandData = new CommandData("remove_first", removeFirstCommandArg);
    CommandArg showCommandArg = new CommandArg(new Class[]{});
    CommandData showCommandData = new CommandData("show", showCommandArg);
//    CommandArg undoCommandArg = new CommandArg(new Class[]{});
//    CommandData undoCommandData = new CommandData("undo",undoCommandArg);
    CommandArg updateIDCommandArg = new CommandArg(new Class[]{Integer.class, Integer.class});
    CommandData updateIDCommandData = new CommandData("update_id", updateIDCommandArg);
    CommandArg executeScriptCommandArg = new CommandArg(new Class[]{String.class});
    CommandData executeScriptCommandData = new CommandData("execute_script", executeScriptCommandArg);
    CommandArg helpCommandArg = new CommandArg(new Class[]{});
    CommandData helpCommandData = new CommandData("help",helpCommandArg);
    CommandArg exitCommandArg = new CommandArg(new Class[]{});
    CommandData exitCommandData = new CommandData("exit",exitCommandArg);


    public final List<String> commandNames = List.of(
            "add",
            "add_if_min",
            "add_if_max",
            "clear",
            "count_greater_than_price",
            "filter_greater_than_comment",
            "info",
            "print_field_descending_price",
            "remove_by_id",
            "remove_first",
            "show",
            "update_id",
            "execute_script",
            "help",
            "exit"
    );

    @AllArgsConstructor
    @Data
    public static class CommandData {
        private String commandName;
        private CommandArg commandArgs;

    }
    @Data
    @AllArgsConstructor
    public static class CommandArg {
        private Class<?>[] arg;
    }
}

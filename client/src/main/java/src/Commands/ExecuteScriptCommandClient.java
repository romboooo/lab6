package src.Commands;


import src.ConsoleProcessor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Stack;


public class ExecuteScriptCommandClient extends AbstractClientCommand {


    final ConsoleProcessor processor;

    Stack<String> pathStack = new Stack<>();

    public ExecuteScriptCommandClient(ConsoleProcessor processor) {
        super();
        this.processor = processor;
    }

    public void ExecuteCommand(String scriptPath) {

        if (scriptPath.isBlank()) {
            System.out.println("command expects argument. please try it one more time");
            return;
        }

        if (pathStack.contains(scriptPath)) {
            System.out.println("recursion :(");
            return ;
        }


        try (Scanner scanner = new Scanner(new FileReader(scriptPath))) {
            pathStack.push(scriptPath);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().strip();
                if (line.isBlank()) {
                    continue;
                }
                processor.execute(line);
            }
            pathStack.pop();

        } catch (FileNotFoundException e) {
            System.out.println("file is not found :(");
        }

    }

}
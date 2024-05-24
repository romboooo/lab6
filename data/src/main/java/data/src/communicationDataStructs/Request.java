package data.src.communicationDataStructs;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Request implements Serializable {
    String commandName;

    String[] commandArgs;
    Object objectArgs;
    public Request(String commandName, String[] commandArgs, Object objectArgs){
        this.commandName = commandName;
        this.commandArgs = commandArgs;
        this.objectArgs = objectArgs;

    }
    public Request(String commandName, String[] commandArgs){
        this.commandName = commandName;
        this.commandArgs = commandArgs;

    }

    public static final long serialVersionUID = 1;

}

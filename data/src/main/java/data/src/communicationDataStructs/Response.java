package data.src.communicationDataStructs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
@Data
@AllArgsConstructor
public class Response implements Serializable {


    public enum status{
        OK,
        FILE_ERROR,
        COLLECTION_IS_EMPTY,
        DATA_LOST,
        ERROR
    }

    String responseMessage;
    public status stat;
    public static final long serialVersionUID = 1;
}

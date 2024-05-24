package data.src.defaultClasses;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvRecurse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @CsvBindByName(column = "birthday", required = true)
    @CsvDate
    private java.time.LocalDateTime birthday; //Поле не может быть null

    @CsvBindByName(column = "weight", required = true)
    private int weight; //Значение поля должно быть больше 0

    @CsvBindByName(column = "passportID", required = true)
    private String passportID; //Значение этого поля должно быть уникальным, Длина строки должна быть не меньше 4, Поле не может быть null

    @CsvRecurse
    private Location location; //Поле не может быть null


    @Override
    public String toString(){
        return this.passportID;
    }


}
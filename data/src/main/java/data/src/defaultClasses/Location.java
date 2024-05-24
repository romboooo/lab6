package data.src.defaultClasses;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Location {

    @CsvBindByName(column = "location.x", required = true)
    private Integer x; //Поле не может быть null
    @CsvBindByName(column = "location.y", required = true)
    private long y;
    @CsvBindByName(column = "location.name", required = true)
    private String name; //Строка не может быть пустой, Поле может быть null
}
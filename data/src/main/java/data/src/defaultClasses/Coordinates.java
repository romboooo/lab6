package data.src.defaultClasses;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Coordinates {
    @Getter
    @CsvBindByName(column = "Coordinates.x", required = true)
    private Float x; //Значение поля должно быть больше -101, Поле не может быть null
    @CsvBindByName(column = "Coordinates.y", required = true)
    private Float y; //Поле не может быть null

    public Coordinates(float x, float y){
        this.x = x;
        this.y = y;
    }


}
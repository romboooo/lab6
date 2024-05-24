package data.src.defaultClasses;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvRecurse;
import data.src.Proccessors.DataBaseProcessor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
public class Ticket implements Serializable{


//    static final String[] FIELD_MAPPING = new String[]{
//            "id", "name", "coordinates.x", "coordinates.y", "user.role.activeFrom", "user.role.owner.name"
//    };

   private static final long serialVersionUID = 345272;


    @CsvBindByName(column = "id", required = true)
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @CsvBindByName (column = "name", required = true)
    private String name; //Поле не может быть null, Строка не может быть пустой

    @CsvRecurse
    private Coordinates coordinates; //Поле не может быть null

    @CsvBindByName (column = "creationDate", required = true)
    @CsvDate
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @CsvBindByName (column = "price", required = true)
    private Long price; //Поле не может быть null, Значение поля должно быть больше 0

    @CsvBindByName (column = "discount", required = true)
    private int discount; //Значение поля должно быть больше 0, Максимальное значение поля: 100

    @CsvBindByName (column = "comment", required = true)
    private String comment; //Строка не может быть пустой, Поле может быть null

    @CsvBindByName (column = "type", required = true)
    private TicketType type; //Поле может быть null

    @CsvRecurse
    private Person person; //Поле может быть null


    @Override
    public boolean equals(Object object){
        if (object.getClass() != Ticket.class) {
            return false;
        }

        return getId() == ((Ticket) object).getId();
    }

    public Ticket(int id,
                  String name,
                  Coordinates coordinates,
                  Date creationDate,
                  Long price,
                  int discount,
                  String comment,
                  TicketType type,
                  Person person){

        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.discount = discount;
        this.comment = comment;
        this.type = type;
        this.person = person;
    }


    public boolean checkID(int id, DataBaseProcessor dataBase){

        return ((dataBase.ArrayOfUniqueID.contains(id) == false) && id > 0);

    }
    public static class Checker {
        public static boolean checkName(String name) {

            return (name != null) && (name.isBlank() == false);
        }

        public static boolean checkCoorinates(Coordinates coordinates) {
            return (coordinates != null);
        }

        public static boolean checkCreationDate(Date creationDate) {
            return (creationDate != null);
        }

        public static boolean checkPrice(Long price) {
            return ((price != null) && (price > 0));
        }

        public static boolean checkDiscount(int discount) {
            return ((discount > 0) && (discount <= 100));
        }

        public static boolean checkComment(String comment) {
            return ((comment == null) || (!comment.isBlank()));
        }

        public static boolean checkType(TicketType type) {
            return type != null;
        }

        public static boolean checkPerson(Person person) {
            return person != null;
        }

        public static boolean checkX(Float x){
            return ((x != null) &&(x >= -1000) && (x <= 1000));
        }
        public static boolean checkY(Float y){
            return ((y != null) && (y >= -1000) && (y <= 1000));
        }
    }


}
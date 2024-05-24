package data.src.Proccessors;

import data.src.defaultClasses.*;
import data.src.defaultClasses.Coordinates;


import java.util.*;

public class DataBaseProcessor extends Processor{

    public ArrayDeque<Ticket> tickets = new ArrayDeque<Ticket>();
    public Set<Integer> ArrayOfUniqueID = new HashSet<Integer>();
    public Stack<HashMap<Ticket, Boolean>> undoStack = new Stack();
    private Set<Integer> IDs = new HashSet<>();





    public static Ticket createNewTicket(int id,
                                         String name,
                                         Coordinates coordinates,
                                         Date creationDate,
                                         Long price,
                                         int discount,
                                         String comment,
                                         TicketType type,
                                         Person person){


        return new Ticket(id, name, coordinates, creationDate,
               price, discount,comment, type, person);

    }

    public int maxID(){

        for(Ticket ticket : this.tickets){
            IDs.add(ticket.getId());
        }
        if(IDs.isEmpty()){
            return 0;
        }

        return Collections.max(IDs);


    }

    public int createID(){

        int newID = maxID() + 1;
        return newID;
    }


 }

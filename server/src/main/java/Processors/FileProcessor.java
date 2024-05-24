package Processors;

import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import data.src.Proccessors.Processor;
import data.src.defaultClasses.Ticket;
import java.io.*;
import java.util.*;
public class FileProcessor extends Processor {

   public static final String CSVFILE = "/Users/rom4ikk/Desktop/учеба/1 курс/2 сем/прога/labb666 2/labb666/server/src/main/java/file/file.csv";

//   public static final String CSVFILE = System.getenv("file");
    public static class Parser {

        public void writeToFile(List<Ticket> collection) {
            MappingStrategy<Ticket> strategy = new HeaderColumnNameMappingStrategy<>();

            strategy.setType(Ticket.class);


            try (FileWriter writer = new FileWriter(CSVFILE)) {

                new StatefulBeanToCsvBuilder(writer)
                        .withSeparator(';')
                        .withApplyQuotesToAll(false)
                        .withMappingStrategy(strategy)
                        .build()
                        .write(collection);
            }catch (FileNotFoundException e){
                System.err.println("file is not found");
            }
            catch (IOException e) {
                System.err.println(e.getMessage());
            } catch (CsvRequiredFieldEmptyException e) {
                System.err.println(e.getMessage());
            } catch (CsvDataTypeMismatchException e) {
                System.err.println(e.getMessage());
            }


        }
        public List<Ticket> readFromFile()  {
            try {
                FileReader reader = new FileReader(CSVFILE);
                List<Ticket> result = new CsvToBeanBuilder<Ticket>(reader)
                        .withType(Ticket.class)
                        .withSkipLines(0)
                        .withSeparator(';')
                        .withIgnoreQuotations(true)
                        .withThrowExceptions(false)
                        .build()
                        .parse();
                return result;
            } catch (FileNotFoundException e){
                System.out.println("file is not found, please, check path");
                return null;
            }

            catch (RuntimeException e){
                System.err.println("something went wrong with your file");
                System.exit(0);
                return null;
            }
        }

    }

}

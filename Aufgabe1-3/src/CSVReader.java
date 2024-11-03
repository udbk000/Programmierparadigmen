import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class CSVReader {

    // This variable stores the entire data of the database in an array, one line per index
    public final String[] data;

    // This variable contains the path to the .csv -file which is used as a data base in this program
    private String path = "Aufgabe1-3/Datenbank_PP2.csv";


    // Constructor for a CSVReader object
    public CSVReader() {
        this.data = readData(path);
    }


    // This method reads the data from the .csv-file and throws exceptions if they occur
    public String[] readData(String path) {

        String[] data = new String[getLengthOfFile(path)];
        String line = "";
        int i = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {
                data[i++] = Arrays.toString(line.split("\n"));
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    // This method returns the number of lines of the .csv-file, so that the array data can be declared with the correct size
    private int getLengthOfFile(String path) {
        int count = 0;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String input;

            while((input = bufferedReader.readLine()) != null)
            {
                count++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
        throw new RuntimeException(e);
        }

        return count;
    }
}

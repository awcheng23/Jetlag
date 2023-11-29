import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FlightData {

    private HashMap<String, Integer> ID; // Map column name to index
    private String csvFile;

    public FlightData(String csvFile) {
        this.csvFile = csvFile;
    }
    /**
     * Get the data from the CSV file into a 2D array format
     * @return an array of the data
     */
    public ArrayList<String[]> get() { 

        ArrayList<String[]> flights = new ArrayList<>(); 

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Read column names and store indices into HashMap
            String[] headers = br.readLine().split(",");
            HashMap<String, Integer> col = new HashMap<>();
            for(int i=0; i<headers.length; i++) {
                col.put(headers[i], i);
            }
            ID = col;

            // Read the rest of the lines
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                flights.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return flights;
    }

     
}

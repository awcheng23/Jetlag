import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FlightData {

    private Map<String, Integer> ID; // Map column name to index
    private List<String[]> flights; // Array of flight data
    private List<String> departAirports;
    private List<String> arrivalAirports;

    public FlightData(String csvFile) {
        get(csvFile);
    }
    /**
     * Get the data from the CSV file into a 2D array format
     * @return an array of the data
     */
    private List<String[]> get(String csvFile) { 

        flights = new ArrayList<>(); 

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Read column names and store indices into HashMap
            String[] headers = br.readLine().split(",");
            ID = new HashMap<>();
            for(int i=0; i<headers.length; i++) {
                ID.put(headers[i], i);
            }

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

    // Get all possible departure airports
    private ArrayList<String> departures() {
        Collections.sort(flights, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1[0].compareTo(o2[0]);
            }
        });
    };

    // Get all possible departure airports
    private ArrayList<String> arrivals() {
        Collections.sort(flights, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1[0].compareTo(o2[0]);
            }
        });
    };
    

    public String[] lowest(String source, String destination) {
        String[] path = new String[flights.get(0).length];



        return path;
    }

}

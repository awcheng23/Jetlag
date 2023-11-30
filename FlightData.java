import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FlightData {

    private Map<String, Integer> ID; // Map column name to index
    private List<String[]> flights; // Array of flight data
    private Set<String> departAirports; // Set of departure airports
    private Set<String> arrivalAirports; // Set of arrival airports

    public FlightData(String csvFile) {
        get(csvFile);
        departures();
        arrivals();
    }
    
    //Get the data from the CSV file into a 2D array format
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
    private Set<String> departures() {
        departAirports = new HashSet<>();
        for(String[] flight : flights) {
            departAirports.add(flight[ID.get("Departure Airport")]);
            departAirports.add(flight[ID.get("1st Stop")]);
            departAirports.add(flight[ID.get("2nd Stop")]);
            departAirports.add(flight[ID.get("3rd Stop")]);
        }
        return departAirports;
    }

    // Get all possible arrival airports
    private Set<String> arrivals() {
        arrivalAirports = new HashSet<>();
        for(String[] flight : flights) {
            arrivalAirports.add(flight[ID.get("1st Stop")]);
            arrivalAirports.add(flight[ID.get("2nd Stop")]);
            arrivalAirports.add(flight[ID.get("3rd Stop")]);
            arrivalAirports.add(flight[ID.get("Arrival Airport")]);
        }
        return arrivalAirports;
    }
    

    public String[] lowest(String source, String destination) {
        String[] path = new String[flights.get(0).length];



        return path;
    }

}

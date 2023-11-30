import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FlightData {

    private Map<String, Integer> ID; // Map column name to index
    private List<String[]> flights; // Array of flight data
    private Set<String> departAirports; // Set of departure airports
    private Set<String> arrivalAirports; // Set of arrival airports

    private final String[] DEPARTURE_STOPS = {"Departure Airport", "1st Stop", "2nd Stop", "3rd Stop"};
    private final String[] ARRIVAL_STOPS = {"Arrival Airport", "1st Stop", "2nd Stop", "3rd Stop"};

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
            for(String stop : DEPARTURE_STOPS) {
                departAirports.add(flight[ID.get(stop)]);
            }
        }
        departAirports.remove("");
        return departAirports;
    }

    // Get all possible arrival airports
    private Set<String> arrivals() {
        arrivalAirports = new HashSet<>();
        for(String[] flight : flights) {
            for(String stop : ARRIVAL_STOPS) {
                arrivalAirports.add(flight[ID.get(stop)]);
            }
        }
        arrivalAirports.remove("");
        return arrivalAirports;
    }
    

    public String[] lowest(String source, String destination) {
        String[] optimal = new String[flights.get(0).length];
        


        return optimal;
    }

    private Graph create() {
        Graph routes = new Graph();
        return routes;
    }

    /**
     * All flights
     * @return a list of all flights
     */
    public List<String[]> getFlights() {return flights;}

    /**
     * Departure airports
     * @return a set of unique departure airports
     */
    public Set<String> getDepartures() {return departAirports;}

    /**
     * Arrival airports
     * @return a set of unique arrival airports
     */
    public Set<String> getArrivals() {return arrivalAirports;}

    public static void main(String[] args) {
        FlightData data = new FlightData("flight.csv");
        System.out.println(data.getDepartures().size());
    }

}

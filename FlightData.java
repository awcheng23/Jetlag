import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Handles flight data
 * 
 * @author Alice Cheng, May Ming
 * @version 0.0.3
 */
public class FlightData {

    private Map<String, Integer> ID; // Map column name to index
    private List<String[]> flights; // Array of flight data
    private Set<String> departAirports; // Set of departure airports
    private Set<String> arrivalAirports; // Set of arrival airports
    private Graph routes; // Graph of all connecting flights

    private final String[] DEPARTURE_STOPS = {"Departure Airport", "1st Stop", "2nd Stop", "3rd Stop"};
    private final String[] ARRIVAL_STOPS = {"1st Stop", "2nd Stop", "3rd Stop", "Arrival Airport"};

    /**
     * Retrieves flight data from a CSV file
     * @param csvFile the path to the flight data csv file
     */
    public FlightData(String csvFile) {
        get(csvFile);
        departures();
        arrivals();
        createGraph();
    }

    // Get the data from the CSV file into a 2D array format
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

    // Gets every departure airport
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

    // Gets every arrival airport
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

    // Creates graph from all possible routes
    private Graph createGraph() {
        routes = new Graph();

        for(int f=0; f<flights.size(); f++) {
            String[] flight = flights.get(f);
            for(int i=0; i<DEPARTURE_STOPS.length; i++){
                for(int j=i; j<ARRIVAL_STOPS.length; j++){
                    int a = ID.get(DEPARTURE_STOPS[i]);
                    int b = ID.get(ARRIVAL_STOPS[j]);
                    int cost = ID.get("Ticket Price (Dollar)");

                    if(!flight[a].isEmpty() && !flight[b].isEmpty())
                        // Add index f to identify which flight the each subpath is from
                        routes.addEdge(flight[a], flight[b], Integer.parseInt(flight[cost]), f);
                }
            }
        }
        return routes;
    }
    
    /**
     * Provides information on the cheapest route between two airports
     * @param source
     * @param destination
     * @return the price of the cheapest path, the airport stops comma delimited, the flight indices comma delimited
     */
    public String[] cheapestRoute(String source, String destination) {        
        return routes.findShortestPath(source, destination);
    }

    /**
     * Gets a list of all flights 
     * @return a list of all flights
     */
    public List<String[]> getFlights() {return flights;}

    /**
     * Gets the set of departure airports
     * @return a set of unique departure airports
     */
    public Set<String> getDepartures() {return departAirports;}

    /**
     * Gets the set of arrival airports
     * @return a set of unique arrival airports
     */
    public Set<String> getArrivals() {return arrivalAirports;}

}

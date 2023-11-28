import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FlightData {

    public static void main(String[] args) { // need to make not main
        String csvFile = "flight.csv";

        ArrayList<String[]> flights = new ArrayList<>(); // each flight

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Read column names and store indices into HashMap
            String[] headers = br.readLine().split(",");
            HashMap<String, Integer> col = new HashMap<>();
            for(int i=0; i<headers.length; i++) {
                col.put(headers[i], i);
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

        System.out.println(Arrays.toString(flights.get(0)));
    }
}

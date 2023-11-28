import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AirplaneData {

    public static void main(String[] args) {
        // Specify the path to your CSV file
        String csvFile = "path/to/your/file.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Read the header line if it contains column names
            String headerLine = br.readLine();
            // You can use the headerLine to identify columns or skip it if not needed

            // Read the rest of the lines
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into an array of values using the comma as a delimiter
                String[] values = line.split(",");

                // Now you can access each value using the array index
                String airlineName = values[0];
                String travelTime = values[1];
                // ... continue for other columns

                // Print or process the values as needed
                System.out.println("Airline: " + airlineName + ", Travel Time: " + travelTime);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

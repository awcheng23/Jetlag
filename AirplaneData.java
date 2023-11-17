import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
public class AirplaneData {
    public static void main(String[] args) {
        String apiKey = "048c2170116ffe905be3926de8aa897a";
        String endpoint = "https://api.aviationstack.com/v1/";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("param1", "value1");
        parameters.put("param2", "value2");

        try {
            URL url = new URL(endpoint + buildQueryString(parameters));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("apikey", apiKey);

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Save the API response to a file
                saveToFile(response.toString(), "flight_data.json");
                System.out.println("Data saved to flight_data.json");
            } else {
                System.out.println("Error: " + responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveToFile(String data, String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String buildQueryString(Map<String, String> parameters) {
        StringBuilder queryString = new StringBuilder("?");
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            queryString.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        // Remove the trailing "&"
        queryString.setLength(queryString.length() - 1);
        return queryString.toString();
    }

}

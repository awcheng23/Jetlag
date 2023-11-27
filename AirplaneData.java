import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class AirplaneData {
    public static void main(String[] args) throws Exception {

        String urlString = "https://api.aviationstack.com/v1/flights";

        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        String apiKey = "access_key:d5890c79bb6123fa6f309e7c6dd5bae0";
        conn.setRequestProperty("Authorization", apiKey);
        conn.setRequestMethod("GET");

        System.out.println(conn.getResponseCode());


        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        StringBuilder result = new StringBuilder();
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();

        // The result variable now contains the raw JSON response.
        // You'll need to parse this JSON string manually as Java's standard library doesn't have a built-in JSON parser.
        // Consider using a library like Gson or Jackson if you need to parse JSON frequently.
        //System.out.println(result.toString());
    }
}

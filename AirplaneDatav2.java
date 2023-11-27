import java.io.*;
import java.net.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AirplaneDatav2 {
    String YOUR_API_KEY = "048c2170116ffe905be3926de8aa897a";
    String apiUrl = "https://api.aviationstack.com/v1/";

    String airport = "KSFO";

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(apiUrl + "airports/" + airport + "/flights"))
            .headers("x-apikey", YOUR_API_KEY)
            .build();
    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

if (response.statusCode() == 200) {
        System.out.println("responseBody: " + response.body());
    }
}

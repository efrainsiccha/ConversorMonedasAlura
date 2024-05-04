package httpServices;

import com.google.gson.Gson;
import conversionServices.Converter;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpConnection {
    public Converter searchCurrency(String basedenomination){
        URI direction = URI.create("https://v6.exchangerate-api.com/v6/5abe5cbc35fc2d6978a071d5/latest/"+basedenomination);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direction)
                .build();
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Converter.class);
        } catch (Exception e) {
            throw new RuntimeException("Error! The conversion was not successful");
        }
    }
}

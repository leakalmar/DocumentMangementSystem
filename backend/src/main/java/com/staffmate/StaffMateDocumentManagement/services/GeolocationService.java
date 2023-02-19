package com.staffmate.StaffMateDocumentManagement.services;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class GeolocationService {

    private final String geoapifyURL = "https://api.geoapify.com/v1/geocode";
    private final String geoapifyAPIKEY = "fbb9255c3a7348158ff66f03a49006b6";

    public GeoPoint getCoordinates(String text) throws IOException {
        return getGeoPoint(text, "/search?text=");
    }
    public GeoPoint getCityCoordinates(String text) throws IOException {
        return getGeoPoint(text, "/search?country=Serbia&city=");
    }

    private GeoPoint getGeoPoint(String text, String x) throws IOException {
        String encoded = URLEncoder.encode(text, StandardCharsets.UTF_8.toString());
        URL url = new URL(geoapifyURL + x + encoded + "&apiKey=" + geoapifyAPIKEY);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestProperty("Accept", "application/json");
        StringBuilder response = readResponse(http);
        JsonObject convertedObject = new Gson().fromJson(response.toString(), JsonObject.class);
        double lon = convertedObject.getAsJsonArray("features").get(0).getAsJsonObject().get("properties").getAsJsonObject().get("lon").getAsDouble();
        double lat = convertedObject.getAsJsonArray("features").get(0).getAsJsonObject().get("properties").getAsJsonObject().get("lat").getAsDouble();
        http.disconnect();

        return new GeoPoint(lat, lon);
    }

    private static StringBuilder readResponse(HttpURLConnection http) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader((http.getInputStream())));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response;
    }
}

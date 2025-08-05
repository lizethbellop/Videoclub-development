package org.thevault.videoclub_desktop.service.film;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.thevault.videoclub_desktop.exception.ServerErrorException;
import org.thevault.videoclub_desktop.model.pojo.Film;
import org.thevault.videoclub_desktop.model.response.ErrorResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ApiServiceFilm {
    private final HttpClient httpClient;
    private final String baseUrl = "http://localhost:8080/thevault";
    private final ObjectMapper objectMapper;

    public ApiServiceFilm(){
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(30))
                .build();
        this.objectMapper = new ObjectMapper();
    }

    public CompletableFuture<List<Film>> getAllFilms(){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/films"))
                .timeout(Duration.ofSeconds(30))
                .header("Content-Type", "application/json")
                .GET().build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    if(response.statusCode() == 200){
                        try{
                            return objectMapper.readValue(response.body(), new TypeReference<List<Film>>() {});
                        } catch (JsonMappingException e) {
                            e.printStackTrace();
                            throw new RuntimeException("Error parsing films films JSON");
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                            throw new RuntimeException("Error processing JSON");
                        }
                    }else{
                        try {
                            throw parseServerError(response);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException("Error processing JSON: " + e.getMessage(), e);
                        }
                    }
                });
    }

    private ServerErrorException parseServerError(HttpResponse<String> response) throws JsonProcessingException{
        String body = response.body();
        ErrorResponse error = objectMapper.readValue(body, ErrorResponse.class);
        return new ServerErrorException("Internal server error: " + error.getMessage());
    }


}

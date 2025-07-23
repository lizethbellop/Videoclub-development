package org.thevault.videoclub_desktop.service.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.thevault.videoclub_desktop.exception.IncorrectCredentialsException;
import org.thevault.videoclub_desktop.exception.ServerErrorException;
import org.thevault.videoclub_desktop.model.UserDTO;
import org.thevault.videoclub_desktop.model.request.LoginRequest;
import org.thevault.videoclub_desktop.model.response.ErrorResponse;
import org.thevault.videoclub_desktop.model.response.LoginResponse;

import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class ApiServiceLogin {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String baseUrl = "http://localhost:8080/thevault";

    public ApiServiceLogin(){
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(30))
                .build();
        this.objectMapper = new ObjectMapper();
    }

    public CompletableFuture<UserDTO> login(String user, String password){
        try {
            LoginRequest request = new LoginRequest(user, password);
            String jsonBody = objectMapper.writeValueAsString(request);

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "/auth/login"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            return httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                    .thenApply(response -> {
                        System.out.println("Respuesta recibida - Status: " + response.statusCode());
                        System.out.println("Body: " + response.body());

                        if(response.statusCode() == 200 || response.statusCode() == 201){
                            try {
                                LoginResponse loginResponse = objectMapper.readValue(response.body(), LoginResponse.class);
                                System.out.println("User created: " + loginResponse);
                                return loginResponse.getUser();
                            } catch (Exception e) {
                                throw new RuntimeException("Error parseando respuesta: " + e.getMessage());
                            }
                        }else{
                            try {
                                ErrorResponse errorResponse = objectMapper.readValue(response.body(), ErrorResponse.class);

                                if(response.statusCode() == 404){
                                    throw new IncorrectCredentialsException("Incorrect credentials: " + errorResponse.getMessage());
                                } else if (response.statusCode() >= 500) {
                                    throw new ServerErrorException("Server error: " + errorResponse.getMessage());
                                }else{
                                    throw new ServerErrorException("Request error: " + errorResponse.getMessage());
                                }
                            } catch (JsonMappingException e) {
                                throw new RuntimeException("Error HTTP " + response.statusCode() +
                                        ": The error response couldn't be mapped");
                            } catch (JsonProcessingException e) {
                                throw new RuntimeException("Error HTTP " + response.statusCode() +
                                        ": The error response couldn't be parsed");
                            }
                        }
                    });
        } catch (JsonProcessingException e) {
            System.err.println("Error preparando peticion: " + e.getMessage());
            return CompletableFuture.failedFuture(e);
        }
    }
}

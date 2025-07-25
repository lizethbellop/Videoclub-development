package org.thevault.videoclub_desktop.service.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.thevault.videoclub_desktop.exception.IncorrectCredentialsException;
import org.thevault.videoclub_desktop.exception.ServerErrorException;
import org.thevault.videoclub_desktop.exception.ValidationException;
import org.thevault.videoclub_desktop.model.UserDTO;
import org.thevault.videoclub_desktop.model.request.LoginRequest;
import org.thevault.videoclub_desktop.model.response.ErrorResponse;
import org.thevault.videoclub_desktop.model.response.LoginResponse;
import org.thevault.videoclub_desktop.model.response.ValidationErrorResponse;

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
                    .thenApply(response -> handleResponse(response));
        } catch (JsonProcessingException e) {
            System.err.println("Error preparando peticion: " + e.getMessage());
            return CompletableFuture.failedFuture(e);
        }
    }

    private UserDTO handleResponse(HttpResponse<String> response){
        int status = response.statusCode();
        String body = response.body();

        System.out.println("Respuesta recibida - Status: " + status);
        System.out.println("Body: " + body);

        try{
            return switch (status){
                case 200, 201 -> parseLoginSuccess(body);
                case 400 -> throw parseInvalidInformation(body);
                case 404 -> throw parseIncorrectCredentials(body);
                case 500 -> throw new ServerErrorException("Internal server error");
                default -> throw parseGenericError(body, status);
            };
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private UserDTO parseLoginSuccess(String body) throws JsonProcessingException{
        LoginResponse loginResponse = objectMapper.readValue(body, LoginResponse.class);
        System.out.println("User created: " + loginResponse);
        return loginResponse.getUser();
    }

    private IncorrectCredentialsException parseIncorrectCredentials(String body) throws JsonProcessingException{
        ErrorResponse error = objectMapper.readValue(body, ErrorResponse.class);
        return new IncorrectCredentialsException("Incorrect credentials: " + error.getMessage());
    }

    private ValidationException parseInvalidInformation(String body) throws JsonProcessingException{
        ValidationErrorResponse errorResponse = objectMapper.readValue(body, ValidationErrorResponse.class);
        return new ValidationException(String.format("Warning you got: %s \n You can see it in more " +
                "detail here: \n %s", errorResponse.getSummary(), errorResponse.getFieldErrors()));
    }

    private RuntimeException parseGenericError(String body, int status) throws JsonProcessingException{
        ErrorResponse error = objectMapper.readValue(body, ErrorResponse.class);
        return new RuntimeException("Error HTTP " + status + ": " + error.getMessage());
    }
}

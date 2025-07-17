package org.thevault.videoclubAPI.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.thevault.videoclubAPI.model.dto.UserSessionDTO;
import org.thevault.videoclubAPI.model.dto.request.LoginRequest;
import org.thevault.videoclubAPI.model.dto.response.LoginResponse;
import org.thevault.videoclubAPI.service.AuthService;

@RestController
@RequestMapping(value = "/thevault/auth/login")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        try{
            UserSessionDTO session = authService.login(request.getUsernameOrEmail(), request.getPassword());
            LoginResponse response = new LoginResponse(session, "Login successful", true);
            return ResponseEntity.ok(response);
        }catch (ResponseStatusException e){
            LoginResponse response = new LoginResponse(null, e.getReason(), false);
            return ResponseEntity.status(e.getStatusCode()).body(response);
        }
    }
}

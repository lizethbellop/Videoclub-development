package org.thevault.videoclub_desktop.controller;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import org.thevault.videoclub_desktop.model.UserDTO;
import org.thevault.videoclub_desktop.service.login.ApiServiceLogin;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Label lbPasswordError;

    @FXML
    private Label lbUserError;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private TextField tfUser;

    private ApiServiceLogin apiServiceLogin;
    private UserDTO obtainedUser;

    @FXML
    void onForgottenClicked(ActionEvent event) {

    }

    @FXML
    void onLoginClicked(ActionEvent event) {
        String username = tfUser.getText().trim();
        String password = pfPassword.getText();

        if(validateFields(username, password)){

        }

    }

    private boolean validateFields(String username, String password){
        lbUserError.setText("");
        lbPasswordError.setText("");
        boolean validFields = true;

        if(username.isEmpty()){
            lbUserError.setText("User required");
            validFields = false;
        }

        if(username.isEmpty()){
            lbPasswordError.setText("Password required");
            validFields = false;
        }

        return validFields;

    }

    private void validateCredentials(String username, String password){
        apiServiceLogin.login(username, password)
                .thenAccept(obtainedUser -> {
                    System.out.println("Login successful");

                    Platform.runLater(() -> {
                        System.out.println("Welcome!");
                    });
                });
                //.exceptionally()
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        apiServiceLogin = new ApiServiceLogin();
    }
}

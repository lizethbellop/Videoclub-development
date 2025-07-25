package org.thevault.videoclub_desktop.controller;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.thevault.videoclub_desktop.exception.ExceptionHandler;
import org.thevault.videoclub_desktop.model.UserDTO;
import org.thevault.videoclub_desktop.service.login.ApiServiceLogin;
import org.thevault.videoclub_desktop.session.SessionManager;
import org.thevault.videoclub_desktop.utils.WindowUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
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


    private static final String FXML_BASE_PATH = "/org/thevault/videoclub_desktop/";


    private static final String EMPLOYEE_FXML = "employeeHomepage.fxml";
    private static final String CLIENT_FXML = "clientHomepage.fxml";

    @FXML
    void onForgottenClicked(ActionEvent event) {

    }

    @FXML
    void onLoginClicked(ActionEvent event) {
        String username = tfUser.getText().trim();
        String password = pfPassword.getText();

        if(validateFields(username, password)){
            validateCredentials(username, password);
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

        if(password.isEmpty()){
            lbPasswordError.setText("Password required");
            validFields = false;
        }

        return validFields;

    }

    private void validateCredentials(String username, String password){
        apiServiceLogin.login(username, password)
                .thenAccept(user -> {
                    this.obtainedUser = user;
                    System.out.println("Login successful: " + user.getUsername());
                    SessionManager.getInstance().setLoggedUser(obtainedUser);

                    Platform.runLater(() -> {
                        System.out.println("Redirect to homepage");
                        WindowUtils.showSimpleAlert(Alert.AlertType.INFORMATION, "Authentication completed", "Welcome!");
                        defineRoleandGo(obtainedUser.getUserType().toString());
                    });
                })
                .exceptionally( throwable -> {
                    Platform.runLater(() -> ExceptionHandler.handleLoginExceptions(throwable));
                    return null;
                });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        apiServiceLogin = new ApiServiceLogin();
    }

    private void defineRoleandGo(String userType){
        String fxmlFile = "";

        String normalizedUserType = userType.toLowerCase(Locale.ROOT);

        switch (normalizedUserType){
            case "employee":
                fxmlFile = EMPLOYEE_FXML;
                break;

            case "client":
                fxmlFile = CLIENT_FXML;
                break;

            default:
                System.err.println("Unknown userType: " + userType);
                return;
        }

        String fullPath = FXML_BASE_PATH + fxmlFile;
        goToHomepage(fullPath);
    }

    private void goToHomepage(String fxmlPath){
        try{
            Stage baseStage = (Stage) tfUser.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent view = loader.load();

            Scene homepageScene = new Scene(view);
            baseStage.setScene(homepageScene);
            baseStage.setTitle("Home");
            baseStage.show();
        }catch(IOException ex){
            ex.printStackTrace();
            WindowUtils.showSimpleAlert(Alert.AlertType.ERROR, "Error with the interface", "We couldn't upload the homepage");
        }
    }
}

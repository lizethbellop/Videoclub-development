package org.thevault.videoclub_desktop.exception;

import javafx.scene.control.Alert;
import org.thevault.videoclub_desktop.utils.WindowUtils;

public class ExceptionHandler {

    public static void handleLoginExceptions(Throwable throwable){
        if(throwable instanceof IncorrectCredentialsException){
            WindowUtils.showSimpleAlert(Alert.AlertType.ERROR, "Authentication failed", "The credentials are incorrect");
        } else if(throwable instanceof ServerErrorException){
            ServerErrorException serverError = (ServerErrorException) throwable;
            WindowUtils.showSimpleAlert(Alert.AlertType.ERROR, "Server error", serverError.getMessage());
        } else{
            WindowUtils.showSimpleAlert(Alert.AlertType.ERROR, "Unexpected error", "An unexpected error occurred, try again later");
        }
    }
}

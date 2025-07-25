package org.thevault.videoclub_desktop.exception;

import javafx.scene.control.Alert;
import org.thevault.videoclub_desktop.utils.WindowUtils;

import java.util.concurrent.CompletionException;

public class ExceptionHandler {

    public static void handleLoginExceptions(Throwable throwable){
        Throwable realCause = (throwable instanceof CompletionException && throwable.getCause() != null) ? throwable.getCause() : throwable;

        if(realCause instanceof IncorrectCredentialsException){
            WindowUtils.showSimpleAlert(Alert.AlertType.ERROR, "Authentication failed", "The credentials are incorrect");
        } else if(realCause instanceof ServerErrorException){
            ServerErrorException serverError = (ServerErrorException) realCause;
            WindowUtils.showSimpleAlert(Alert.AlertType.ERROR, "Server error", serverError.getMessage());
        } else if (realCause instanceof ValidationException) {
            ValidationException validationException = (ValidationException) realCause;
            WindowUtils.showSimpleAlert(Alert.AlertType.ERROR, "Problems with the information given", validationException.getMessage());
        } else{
            WindowUtils.showSimpleAlert(Alert.AlertType.ERROR, "Unexpected error", "An unexpected error occurred, try again later");
        }
    }
}

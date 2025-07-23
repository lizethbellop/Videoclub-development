package org.thevault.videoclub_desktop.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class WindowUtils {

    public static void showSimpleAlert(Alert.AlertType type, String title, String content){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        if (type == Alert.AlertType.NONE) {
            alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
        }
        alert.showAndWait();
    }
}

module org.thevault.videoclub_desktop {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.thevault.videoclub_desktop to javafx.fxml;
    exports org.thevault.videoclub_desktop;
    exports org.thevault.videoclub_desktop.controller;
    opens org.thevault.videoclub_desktop.controller to javafx.fxml;
}
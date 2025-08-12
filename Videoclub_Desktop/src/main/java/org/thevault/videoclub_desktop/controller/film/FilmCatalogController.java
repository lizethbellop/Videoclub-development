package org.thevault.videoclub_desktop.controller.film;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import org.thevault.videoclub_desktop.exception.ExceptionHandler;
import org.thevault.videoclub_desktop.model.pojo.Film;
import org.thevault.videoclub_desktop.service.film.ApiServiceFilm;
import org.thevault.videoclub_desktop.utils.ImageLoaderUtil;
import org.thevault.videoclub_desktop.utils.WindowUtils;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class FilmCatalogController implements Initializable {
    @FXML
    private TilePane catalogContainer;

    @FXML
    private ImageView ivProfilePicture;

    private ApiServiceFilm filmService;

    @FXML
    void onAdventureClicked(ActionEvent event) {

    }

    @FXML
    void onComedyClicked(ActionEvent event) {

    }

    @FXML
    void onDramaClicked(ActionEvent event) {

    }

    @FXML
    void onFamilyClicked(ActionEvent event) {

    }

    @FXML
    void onForeignClicked(ActionEvent event) {

    }

    @FXML
    void onHistoricalClicked(ActionEvent event) {

    }

    @FXML
    void onHorrorClicked(ActionEvent event) {

    }

    @FXML
    void onMisteryClicked(ActionEvent event) {

    }

    @FXML
    void onMusicalClicked(ActionEvent event) {

    }

    @FXML
    void onReturnClicked(ActionEvent event) {
        //TODO return to homepage
    }

    @FXML
    void onRomanceClicked(ActionEvent event) {

    }

    @FXML
    void onScifiClicked(ActionEvent event) {

    }

    @FXML
    void onSuperheroClicked(ActionEvent event) {

    }

    private void loadAllFilms(){
        filmService.getAllFilms()
                .thenAccept(films -> {
                    Platform.runLater(() -> {
                        catalogContainer.getChildren().clear();
                        films.forEach(this::createFilmButton);
                    });
                })
                .exceptionally(throwable -> {
                    Platform.runLater(() -> ExceptionHandler.handleCatalogExceptions(throwable));
                    return null;
                });
    }

    private void createFilmButton(Film film){
        Image placeholder = new Image("/images/loading.png");

        ImageView posterImageView = ImageLoaderUtil.loadImageAsync(film.getImageUrl(), 128, 192, placeholder, ex -> {
            Platform.runLater(() -> WindowUtils.showSimpleAlert(Alert.AlertType.ERROR, "An error occured", "The poster couldn't be uploaded"));
        });

        Button filmButton = new Button();
        filmButton.setGraphic(posterImageView);
        filmButton.setOnAction(e -> goToFilmDetails());
        catalogContainer.getChildren().add(filmButton);
    }

    private void goToFilmDetails(){
        //TODO FilmDetails
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        filmService = new ApiServiceFilm();
        loadAllFilms();
    }
}

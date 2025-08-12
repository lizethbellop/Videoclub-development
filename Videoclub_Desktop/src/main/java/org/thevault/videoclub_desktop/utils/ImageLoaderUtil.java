package org.thevault.videoclub_desktop.utils;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.thevault.videoclub_desktop.exception.ImageUploadException;
import java.util.function.Consumer;

public class ImageLoaderUtil {

    public static ImageView loadImageAsync(String imageUrl, double width, double height, Image placeholder, Consumer<Exception> onError){
        ImageView imageView = new ImageView();
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(true);

        if(placeholder != null){
            imageView.setImage(placeholder);
        }

        Image image = new Image(imageUrl, true);
        image.exceptionProperty().addListener((obs, oldVal,ex) -> {
            if(ex != null){
                System.err.println("Error uploading the image: " + ex.getMessage());
                ImageUploadException customEx = new ImageUploadException("Error uploading the image: " + ex.getMessage());
                if(onError != null)
                    onError.accept(customEx);
            }
        });

        image.progressProperty().addListener((obs, oldVal, newVal) -> {
            if(newVal.doubleValue() >= 1.0){
                imageView.setImage(image);
            }
        });

        return imageView;
    }
}

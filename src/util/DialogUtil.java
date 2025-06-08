package util;

import javafx.animation.PauseTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class DialogUtil {
    public static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        Label timerLabel = new Label("Menutup otomatis dalam 5 detik");
        VBox vbox = new VBox(new Label(message), timerLabel);
        vbox.setSpacing(10);
        alert.getDialogPane().setContent(vbox);
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(1), _ -> {
                String text = timerLabel.getText();
                int detik = Integer.parseInt(text.replaceAll("\\D", ""));
                if (detik > 1) {
                    timerLabel.setText("Menutup otomatis dalam " + (detik - 1) + " detik");
                }
            })
        );
        timeline.setCycleCount(5);
        timeline.play();
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(_ -> {
            if (alert.isShowing()) alert.close();
        });
        delay.play();
        alert.showAndWait();
    }
}

package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainApp extends Application {
    static {
        System.load(System.getProperty("user.dir") + "\\bin\\opencv_java4110.dll");
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Font font1 = Font.loadFont(getClass().getResourceAsStream("/fonts/fa-solid-900.ttf"), 18);
        if (font1 != null)
            System.out.println("FontAwesome berhasil dimuat dari /fonts: " + font1.getName());
        else
            System.out.println("FontAwesome gagal dimuat! Cek path dan file font.");

        Parent root = FXMLLoader.load(getClass().getResource("/ui/SmartParking.fxml"));
        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background:transparent;");

        primaryStage.setTitle("Smart Parking UNY");
        primaryStage.setScene(new Scene(scrollPane, 500, 300));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

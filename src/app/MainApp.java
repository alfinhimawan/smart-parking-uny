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
        // Pastikan native library OpenCV diload sebelum apapun
        System.load(System.getProperty("user.dir") + "\\bin\\opencv_java4110.dll");
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        Font font1 = Font.loadFont(getClass().getResourceAsStream("/fonts/fa-solid-900.ttf"), 18);
        Font font2 = Font.loadFont(getClass().getResourceAsStream("../fonts/fa-solid-900.ttf"), 18);
        Font font3 = Font.loadFont(getClass().getResourceAsStream("../../fonts/fa-solid-900.ttf"), 18);
        Font font4 = Font.loadFont(getClass().getResourceAsStream("/bin/fonts/fa-solid-900.ttf"), 18);
        Font font5 = Font.loadFont(getClass().getResourceAsStream("/src/fonts/fa-solid-900.ttf"), 18);
        if (font1 != null)
            System.out.println("FontAwesome berhasil dimuat dari /fonts: " + font1.getName());
        if (font2 != null)
            System.out.println("FontAwesome berhasil dimuat dari ../fonts: " + font2.getName());
        if (font3 != null)
            System.out.println("FontAwesome berhasil dimuat dari ../../fonts: " + font3.getName());
        if (font4 != null)
            System.out.println("FontAwesome berhasil dimuat dari /bin/fonts: " + font4.getName());
        if (font5 != null)
            System.out.println("FontAwesome berhasil dimuat dari /src/fonts: " + font5.getName());
        if (font1 == null && font2 == null && font3 == null && font4 == null && font5 == null)
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

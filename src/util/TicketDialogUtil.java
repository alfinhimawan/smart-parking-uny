package util;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class TicketDialogUtil {
    public static void showTicketDialog(String kodeUnik, String barcodePath) {
        try {
            Image barcodeImg = new Image(new FileInputStream(barcodePath));
            ImageView barcodeView = new ImageView(barcodeImg);
            barcodeView.setFitWidth(200);
            barcodeView.setFitHeight(200);
            barcodeView.setPreserveRatio(true);
            Label title = new Label("Selamat Datang di Parkiran IDB FT UNY");
            title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 0 0 10 0; -fx-alignment: center;");
            Label kodeLabel = new Label(kodeUnik);
            kodeLabel.setStyle("-fx-font-size: 14px; -fx-padding: 10 0 0 0; -fx-alignment: center;");
            Label info = new Label("Digunakan untuk keluar parkiran");
            info.setStyle("-fx-font-size: 13px; -fx-padding: 10 0 0 0; -fx-alignment: center;");
            Button downloadBtn = new Button("Download Barcode");
            downloadBtn.setStyle(
                "-fx-background-color: #2980b9; -fx-text-fill: white; -fx-font-size: 13px; -fx-padding: 6 16 6 16; -fx-background-radius: 8;");
            downloadBtn.setOnAction(_ -> {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Simpan Tiket Barcode");
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Image", "*.png"));
                fileChooser.setInitialFileName("tiket-" + kodeUnik + ".png");
                java.io.File dest = fileChooser.showSaveDialog(null);
                if (dest != null) {
                    try (FileInputStream in = new FileInputStream(barcodePath);
                         FileOutputStream out = new FileOutputStream(dest)) {
                        byte[] buf = new byte[4096];
                        int len;
                        while ((len = in.read(buf)) > 0)
                            out.write(buf, 0, len);
                    } catch (Exception ex) {
                        DialogUtil.showAlert("Gagal menyimpan barcode!");
                    }
                }
            });
            VBox vbox = new VBox(10, title, barcodeView, kodeLabel, info, downloadBtn);
            vbox.setStyle("-fx-padding: 20; -fx-alignment: center;");
            VBox.setVgrow(barcodeView, Priority.ALWAYS);
            Dialog<Void> dialog = new Dialog<>();
            dialog.setTitle("Tiket Parkir");
            dialog.getDialogPane().setContent(vbox);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.showAndWait();
        } catch (Exception e) {
            DialogUtil.showAlert("Gagal menampilkan tiket parkir: " + e.getMessage());
        }
    }
}

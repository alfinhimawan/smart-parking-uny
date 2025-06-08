package service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileInputStream;
import javafx.scene.image.Image;

public class TicketService {
    public String generateTicketBarcode(String kodeUnik, String folder) throws Exception {
        String barcodePath = folder + "/tiket-" + kodeUnik + ".png";
        BitMatrix matrix = new MultiFormatWriter().encode(kodeUnik, BarcodeFormat.QR_CODE, 300, 300);
        Path path = Paths.get(barcodePath);
        if (!Files.exists(path.getParent()))
            Files.createDirectories(path.getParent());
        MatrixToImageWriter.writeToPath(matrix, "PNG", path);
        return barcodePath;
    }

    public boolean deleteTicketBarcode(String kodeUnik, String folder) {
        String barcodePath = folder + "/tiket-" + kodeUnik + ".png";
        java.io.File barcodeFile = new java.io.File(barcodePath);
        if (barcodeFile.exists()) {
            return barcodeFile.delete();
        }
        return false;
    }

    public Image loadTicketBarcodeImage(String barcodePath) throws Exception {
        FileInputStream fis = new FileInputStream(barcodePath);
        return new Image(fis);
    }
}

package generator;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class BarcodeGenerator {
    public static void main(String[] args) throws Exception {
        String[] types = {"mahasiswa", "dosen", "staff", "umum", "pelajar" };
        int width = 300, height = 300;
        for (String tipe : types) {
            String barcode = tipe.toUpperCase();
            String fileName = "src/barcodes_civitas/" + tipe + ".png";
            Path path = Paths.get(fileName);
            if (!Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
            if (Files.exists(path)) {
                System.out.println("Barcode untuk " + barcode + " sudah ada, dilewati.");
                continue;
            }
            BitMatrix matrix = new MultiFormatWriter().encode(barcode, BarcodeFormat.QR_CODE, width, height);
            MatrixToImageWriter.writeToPath(matrix, "PNG", path);
            System.out.println("Barcode untuk " + barcode + " disimpan di " + path);
        }
    }
}

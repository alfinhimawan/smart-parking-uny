package service;

import org.opencv.core.Mat;
import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import java.awt.image.BufferedImage;
import java.io.File;

public class BarcodeService {
    public String decode(Mat frame, BufferedImage bufferedImage, String barcodeFilePath) {
        try {
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Result result = new MultiFormatReader().decode(bitmap);

            File barcodeFile = new File(barcodeFilePath);
            if (barcodeFile.exists()) {
                if (barcodeFile.delete()) {
                    System.out.println("File barcode berhasil dihapus: " + barcodeFilePath);
                } else {
                    System.err.println("Gagal menghapus file barcode: " + barcodeFilePath);
                }
            } else {
                System.err.println("File barcode tidak ditemukan: " + barcodeFilePath);
            }

            return result.getText();
        } catch (Exception e) {
            return null;
        }
    }

    public String decode(Mat frame, BufferedImage bufferedImage) {
        try {
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (Exception e) {
            return null;
        }
    }
}
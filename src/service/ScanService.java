package service;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import org.opencv.core.Mat;
import util.ImageUtil;
import java.awt.image.BufferedImage;
import java.util.function.Consumer;

public class ScanService {
    private Thread scanThread;
    private CameraService cameraService;
    private BarcodeService barcodeService;

    public ScanService(CameraService cameraService, BarcodeService barcodeService) {
        this.cameraService = cameraService;
        this.barcodeService = barcodeService;
    }

    public void startScan(ImageView cameraView, Consumer<String> onBarcodeDetected) {
        stopScan();
        cameraView.setVisible(true);
        cameraView.setManaged(true);
        cameraService.startCamera(0);
        scanThread = new Thread(() -> {
            boolean barcodeProcessed = false;
            while (cameraView.isVisible() && !Thread.currentThread().isInterrupted()) {
                Mat frame = cameraService.grabFrame();
                if (frame != null && !frame.empty()) {
                    javafx.scene.image.Image img = ImageUtil.mat2Image(frame);
                    Platform.runLater(() -> {
                        if (cameraView != null)
                            cameraView.setImage(img);
                    });
                    BufferedImage bimg = ImageUtil.matToBufferedImage(frame);
                    String kode = barcodeService.decode(frame, bimg);
                    if (kode != null && kode.length() > 0 && !barcodeProcessed) {
                        barcodeProcessed = true;
                        Platform.runLater(() -> onBarcodeDetected.accept(kode));
                        break;
                    }
                }
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        scanThread.setDaemon(true);
        scanThread.start();
    }

    public void stopScan() {
        if (scanThread != null && scanThread.isAlive())
            scanThread.interrupt();
        cameraService.stopCamera();
    }
}

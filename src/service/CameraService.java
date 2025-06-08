package service;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

public class CameraService {
    private VideoCapture capture;
    private boolean cameraActive = false;

    public boolean startCamera(int cameraIndex) {
        if (capture == null) {
            capture = new VideoCapture();
        }
        cameraActive = capture.open(cameraIndex);
        return cameraActive;
    }

    public void stopCamera() {
        if (capture != null && capture.isOpened()) {
            capture.release();
        }
        cameraActive = false;
    }

    public boolean isCameraActive() {
        return cameraActive;
    }

    public Mat grabFrame() {
        if (capture != null && capture.isOpened()) {
            Mat frame = new Mat();
            capture.read(frame);
            return frame;
        }
        return null;
    }
}

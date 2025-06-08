package util;

import javafx.scene.image.Image;
import org.opencv.core.Mat;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import javafx.embed.swing.SwingFXUtils;

public class ImageUtil {
    public static BufferedImage matToBufferedImage(Mat frame) {
        int width = frame.width(), height = frame.height(), channels = frame.channels();
        byte[] sourcePixels = new byte[width * height * channels];
        frame.get(0, 0, sourcePixels);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);
        return image;
    }

    public static Image mat2Image(Mat frame) {
        BufferedImage image = matToBufferedImage(frame);
        return SwingFXUtils.toFXImage(image, null);
    }
}

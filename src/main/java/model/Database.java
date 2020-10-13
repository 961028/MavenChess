package model;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

class Database {

    String whiteFileName = "w";
    String blackFileName = "b";
    String imagesPath = "src/main.sample/images/";
    String fileFormat = "png";

    Map<String, String> imageMap;

    void saveImage(String pieceName, RenderedImage whiteImage, RenderedImage blackImage) throws IOException {
        ImageIO.write(whiteImage, fileFormat, new File(imagesPath + whiteFileName + "_" + pieceName + "." + fileFormat));
        ImageIO.write(blackImage, fileFormat, new File(imagesPath + blackFileName + "_" + pieceName + "." + fileFormat));
    }

    Image readImage(String pieceName, PlayerColor color) throws IOException {
        File file;
        if (color == PlayerColor.WHITE) {
            file = new File(imagesPath + whiteFileName + "_" + pieceName + "." + fileFormat);
        } else {
            file = new File(imagesPath + blackFileName + "_" + pieceName + "." + fileFormat);
        }
        BufferedImage image = ImageIO.read(file);
        return null;
    }
}

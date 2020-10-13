package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CapturedPieceController extends AnchorPane {

    @FXML
    AnchorPane capturedPieceAnchor;
    @FXML
    ImageView capturedPieceImageView;
    @FXML
    Label capturedPieceLabel;

    int positionX;
    int positionY;

    public CapturedPieceController(String name, int posX, int posY)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/fxml_sheets/CapturedPiece.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        positionX = posX;
        positionY = posY;

        capturedPieceAnchor.toFront();

        capturedPieceLabel.setText(name);
    }

}

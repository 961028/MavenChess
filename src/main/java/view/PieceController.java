package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import model.ModelInterface;
import model.PlayerColor;

import java.io.IOException;

public class PieceController extends AnchorPane{

    @FXML
    AnchorPane pieceAnchor;
    @FXML
    Button pieceButton;

    ModelInterface model;
    int positionX;
    int positionY;

    Image image;


    public PieceController(int positionX, int positionY, String name, PlayerColor color, ModelInterface model)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml_sheets/Piece.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.model = model;

        this.positionX = positionX;
        this.positionY = positionY;

        setColor(color);

        pieceAnchor.toFront();

        pieceButton.setText(name);
    }


    private void setColor(PlayerColor color) //Changes the color on the image depending of with side it is on
    {
        //TODO
    }

    @FXML
    public void clicked()
    {
        model.selectPiece(positionX, positionY);
    }

}

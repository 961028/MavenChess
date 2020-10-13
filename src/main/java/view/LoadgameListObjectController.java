package view;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class LoadgameListObjectController extends AnchorPane {

    String name; //The name of the game
    NewGameMenuController menu; //The menu that holds this list object

    @FXML
    Label nameText; //The fxml label that displays the name
    @FXML
    Pane background; //The background of the label

    public LoadgameListObjectController(String name, NewGameMenuController menu)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml_sheets/LoadgameListObject.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.name = name;
        this.menu = menu;

        nameText.setText(name);
    }

    public void uncheck()
    {
        background.setOpacity(0);
    }

    @FXML
    public void clicked()
    {
        menu.uncheckAll();
        background.setOpacity(1);
        //TODO Send what game that the model should load in the two panel selector
    }
}

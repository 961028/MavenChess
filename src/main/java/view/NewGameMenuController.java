package view;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class NewGameMenuController{

    //region variables
    List<String> games = new ArrayList<>();
    //endregion



    //region FXML variables
    @FXML
    private VBox gameListBox; //The VBox that holds the different games

    @FXML
    private Label nameLabel; //The name label of the selected game //TODO Method that assigns this
    @FXML
    private TextArea descriptionText; //the description text for the selected game //TODO Method that assigns this

    @FXML
    private CheckBox isTurnTimerBox; //Does the match have a turn timer?
    @FXML
    private TextField timerField; //The turn timer time in seconds

    @FXML
    private CheckBox isCompetitiveModeBox; //Does the match have competitive mode?
    @FXML
    private TextField competitiveTimerField; //The competitive mode time in minutes
    //endregion

    //region methods
    public void uncheckAll()
    {
        for (int i = 0; i < gameListBox.getChildren().size(); i++)
        {
            try
            {
                ((LoadgameListObjectController) gameListBox.getChildren().get(i)).uncheck(); //TODO kan någon snälla fixa detta. Det ser inte bra ut asså
            } catch (ClassCastException e) {}
        }
    }

    public void setGames(List<String> games) //Call this method to assign the games
    {
        this.games = games;
        games.forEach((game) -> gameListBox.getChildren().add(new LoadgameListObjectController(game, this)));
    }
    //endregion

    //region FXML methods
    @FXML
    public void turnTimerUpdate(){} //TODO updates the turn timer setting
    @FXML
    public void competitiveModeUpdate(){} //TODO updates the turn timer setting
    @FXML
    public void startMatch(){} //TODO start the match
    //endregion
}

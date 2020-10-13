package view;

import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import model.ModelInterface;
import model.PossibleMove;

import java.io.IOException;

public class TokenController extends AnchorPane {

    PossibleMove type;
    boolean isEnemy;
    int xPosition;
    int yPosition;
    ModelInterface model;

    @FXML
    AnchorPane iMove; //green
    @FXML
    AnchorPane enemyMove; //orange
    @FXML
    AnchorPane iAttack; //danger arrows
    @FXML
    AnchorPane canDie; // Exclamation mark
    @FXML
    AnchorPane canUpgrade; //star
    @FXML
    AnchorPane cantMove; //Cross

    public TokenController(PossibleMove type, boolean isEnemy, int xPosition, int yPosition, ModelInterface model) //TODO get enum as parameter, send to setToken()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml_sheets/Token.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.model = model;
        this.type = type;
        this.isEnemy = isEnemy;
        setToken();
    }

    private void setToken () //uptadet the symbols on the tokens depending on the type
    {
        disableAllSymbols();
        switch (type) {
            case MOVE:
                if (!isEnemy) setSymbol(iMove);
                else setSymbol(enemyMove);
                break;
            case CAPTURE:
                setSymbol(iAttack);
                if (isEnemy) setSymbol(canDie);
                break;
            case MOVEANDCAPTURE:
                setSymbol(iAttack);
                if(isEnemy) setSymbol(canDie);
                break;
            case BLOCKEDMOVE:
                setSymbol(cantMove);
                break;
            case UPGRADE:
                setSymbol(canUpgrade);
                break;
        }
    }

    private void setSymbol(AnchorPane setActive) //sets the symbol to visible
    {
        setActive.setVisible(true);
    }

    private void disableAllSymbols() //disables all symbols
    {
        iMove.setVisible(false);
        iAttack.setVisible(false);
        canUpgrade.setVisible(false);
        canDie.setVisible(false);
        enemyMove.setVisible(false);
        cantMove.setVisible(false);
    }

    @FXML
    public void clicked()
    {
        model.movePiece(xPosition, yPosition);
    }
}

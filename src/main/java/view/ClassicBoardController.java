package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;
import model.ModelInterface;
import model.PlayerColor;
import model.PossibleMoveData;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClassicBoardController implements Initializable, BoardObserver {


    @FXML
    AnchorPane boardPieceParentPane; //The pane which pieces and tokens should be added to
    @FXML
    AnchorPane wPieceCollectionPane; //The collection of captured white pieces
    @FXML
    AnchorPane bPieceCollectionPane; //The collection of captured white pieces
    @FXML
    Label bTimer;
    @FXML
    Label wTimer;


    ModelInterface model;

    ArrayList<AnchorPane> pieceList = new ArrayList<AnchorPane>();
    ArrayList<AnchorPane> tokenList = new ArrayList<AnchorPane>();
    ArrayList<AnchorPane> wCapturedPieces = new ArrayList<AnchorPane>(); //Two lists of the captured pieces. Used to show the pieces at the side of the board
    ArrayList<AnchorPane> bCapturedPieces = new ArrayList<AnchorPane>();

    public ClassicBoardController(ModelInterface modelInterface) {
        model = modelInterface;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        model.startNewGame("classic");
    }


    public void placeWiget(AnchorPane pane, int xPos, int yPos)
    {
        int[] realPos = getRealPosition(xPos, yPos);
        boardPieceParentPane.getChildren().add(pane);
        pane.setLayoutX(realPos[0]);
        pane.setLayoutY(realPos[1]);
    }
    
    //todo: if getRealPosition-methods are generalized, these two place-methods can easily be merged to avoid code duplication

    public void placeCapturedPieces(AnchorPane parentPane, AnchorPane pane, int xPos, int yPos)
    {
        int[] realPos = getRealPositionCapturedPieces(xPos, yPos);
        parentPane.getChildren().add(pane);
        pane.setLayoutX(realPos[0]);
        pane.setLayoutY(realPos[1]);
    }



    public void updateTokens(List <PossibleMoveData> possibleMoves, boolean isEnemy){

        for (int i = 0; i < tokenList.size(); i++)          //Clear previous tokens
            {
                boardPieceParentPane.getChildren().remove(tokenList.get(i));
            }

            tokenList = new ArrayList<>();

            for (PossibleMoveData p : possibleMoves)                   //place tokens
            {
                tokenList.add(new TokenController(p.possibleMove, isEnemy, p.x, p.y, model));
                placeWiget(tokenList.get(tokenList.size() - 1), p.x, p.y);
            }
        }




    public void updateBoardState (String[][]names, PlayerColor[][] colors) throws Exception
    {

        boardPieceParentPane.getChildren().clear();

        pieceList = new ArrayList<>();

        if(colors.length != names.length) throw new Exception("Matrixes didn't match");

        for(int i = 0; i < names.length; i++)
        {
            if (colors[i].length != names[i].length) throw new Exception("Matrixes didn't match");

            for (int j = 0; j < names[i].length; j++)
            {
                //System.out.print((j+1)*(i+1)+ " "+ names[i][j] + "\n"); //Detta är endast ett test
                if(names[i][j] != null && colors[i][j] != null) {
                    pieceList.add(new PieceController(i, j, names[i][j], colors[i][j], model));
                    placeWiget(pieceList.get(pieceList.size()-1), i, j);
                }
            }
        }
    }

    @Override
    public void activateUpgrade(List<String> upgrades) {
        // TODO fixa meny till uppgradering av pjäser
    }

    public void updateCapturedPieces(List<String> wList, List<String> bList) { //Updates and displays captured pieces.
        wCapturedPieces.clear();
        bCapturedPieces.clear();

        int[] gridPosition;

        for (int i = 0; i < wList.size(); i++){ //For each (white) list item, a grid position is created and used to create CapturedPieces and place them on their collectionPane
            gridPosition = createGridPositionCapturedPieces(i);
            wCapturedPieces.add(new CapturedPieceController(wList.get(i), gridPosition[0], gridPosition[1]));
            placeCapturedPieces(wPieceCollectionPane, wCapturedPieces.get(i-1), gridPosition[0], gridPosition[1]);
        }

        for (int i = 0; i < bList.size(); i++){ //For each (black) list item, a grid position is created and used to create CapturedPieces and place them on their collectionPane
            gridPosition = createGridPositionCapturedPieces(i);
            bCapturedPieces.add(new CapturedPieceController(bList.get(i), gridPosition[0], gridPosition[1]));
            placeCapturedPieces(bPieceCollectionPane, bCapturedPieces.get(i-1), gridPosition[0], gridPosition[1]);
        }


    }


   private int[] getRealPosition (int x, int y) //Takes the position from the model and converts it to screen position
    {
        int[] arr = {9 + x*550/8, 9 + y*550/8};
        return arr;
    }

    //todo: Make these two "getRealPosition.."-methods into one, adapted for both uses and for responsive design
    //Jag försökte med detta ^ men blev lite svårt pga att komponenterna inte har någon bredd/höjd förrän de är utritade /paint

    private int[] getRealPositionCapturedPieces (int x, int y) //Takes the X and Y positions (0-3) and converts it to screen position for captured pieces
    {
        int[] arr = {6 + x*210/4, 6 + y*210/4};
        return arr;
    }

    private int[] createGridPositionCapturedPieces(int positionInList){ //Creates X and Y positions (0-3) for captured pieces to be used when placed in their collectionPane (in the place-function)
        int xPos = 0, yPos = 0;

        for (int i = 0; i < positionInList; i++){
            xPos = i % 4;
            if (xPos == 0) {
                yPos++;
            }
        }

        int[] arr = {xPos, yPos};
        return arr;
    }


    public void updateTimer(String time, PlayerColor color){ //todo: color parameter can be something else, or this function could be split into one for each color
        if (color == PlayerColor.BLACK)
            bTimer.setText(time);       //This method changes the timer labels. Should be called from backend every second.
        else
            wTimer.setText(time);
    }


}



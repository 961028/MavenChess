package view;

import model.PlayerColor;
import model.PossibleMoveData;
import java.util.List;

public interface BoardObserver {
    void updateTokens(List<PossibleMoveData> possibleMoves, boolean isEnemy);
    void updateBoardState(String[][] names, PlayerColor[][] colors) throws Exception;
    void updateCapturedPieces(List<String> wList, List<String> bList);
    void updateTimer(String time, PlayerColor color);
    void activateUpgrade(List<String> upgrades);
}

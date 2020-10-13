package model;

import view.BoardObserver;

import java.util.List;


public interface ModelObservable {
    void addObserver(BoardObserver observer);
    void pushBoardState(String[][] names, PlayerColor[][] colors);
    void pushTokens(List<PossibleMoveData> possibleMoves, boolean opponentPiece);
    void pushUpgradeList(List<String> upgrades);
}

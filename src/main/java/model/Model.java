package model;

import view.BoardObserver;

import java.util.ArrayList;
import java.util.List;

public class Model implements ModelInterface, ModelObservable {

    ChessGame game;
    //region MAIN MENU
    public void startNewGame(String settings) {
        GameSettings gameSettings = new GameSettings(8, 8);

        if (settings.equals("classic")) {
            gameSettings.populateClassicChess();
        }

        game = new ChessGame(gameSettings);

        updateBoard();
    }

    public void loadGame(ChessGame game) {
        this.game = game;
    }
    //endregion


    //region CONTROLLER INPUT
    /**
     * Moves the Piece selected by selectPiece. If successful, updates frontend.
     * @param x position to move selected piece to
     * @param y position to move selected piece to
     */
    public void movePiece(int x, int y) {
        if (game.isInUpgradePosition(x, y) && game.movePiece(x, y)) {
            pushUpgradeList(game.getPossibleUpgradesList(x,y));
            updateBoard();
        } else if (game.movePiece(x, y)) {
            updateBoard();
        }
    }

    /**
     * Selects a Piece and updates frontend with possible moves. If the Piece was already selected it will be deselected.
     * @param x position of piece to select.
     * @param y position of piece to select.
     */
    public void selectPiece(int x, int y) {
        game.setSelectedPiece(x, y);

        List<PossibleMoveData> possibleMoves = game.getPossibleMoves();
        boolean opponentPiece = game.getCurrentPlayer() != game.getPiece(x, y).getColor();

        pushTokens(possibleMoves, opponentPiece);
    }

    @Override
    public void selectedUpgrade(String upgrade, int x, int y) {
        updateBoard();
        game.upgradePiece(upgrade, x, y);
    }
    //endregion

    private void updateBoard() {
        CustomPair[][] boardState = game.getBoardState();

        int width = boardState.length;
        int height = boardState[0].length;

        PlayerColor[][] boardStateColor = new PlayerColor[width][height];
        String[][] boardStateNames = new String[width][height];


        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (boardState[x][y] != null) {
                    boardStateColor[x][y] = boardState[x][y].getKey();
                    boardStateNames[x][y] = boardState[x][y].getValue();
                }
            }
        }

        pushBoardState(boardStateNames, boardStateColor);
    }

    public List<String> getCapturedBlackPieces() {
        return game.getCapturedBlackPieces();
    }

    public List<String> getCapturedWhitePieces() {
        return game.getCapturedWhitePieces();
    }

    //region OBSERVER METHODS
    List<BoardObserver> observers = new ArrayList<BoardObserver>();

    public void pushUpgradeList(List<String> upgrades) {
        for (BoardObserver observer : observers) {
            observer.activateUpgrade(upgrades);
        }
    }

    public void addObserver(BoardObserver observer) {
        observers.add(observer);
    }

    public void pushBoardState(String[][] names, PlayerColor[][] colors) {
        for (BoardObserver observer : observers) {
            try {
                observer.updateBoardState(names, colors);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void pushTokens(List<PossibleMoveData> tokens, boolean opponentPiece) {
        for (BoardObserver observer : observers) {
            observer.updateTokens(tokens, opponentPiece);
        }
    }
    //endregion
}

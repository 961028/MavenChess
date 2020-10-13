package model;

import java.util.ArrayList;
import java.util.List;

class ChessGame {

    private Board board;

    Piece selectedPiece;
    private PlayerColor currentPlayer = PlayerColor.WHITE;

    ChessGame(GameSettings settings) {
        board = new Board(settings);
    }

    /**
     * Binds your instance variable to your selected piece
     * @param x x coordinate of selected piece
     * @param y y coordinate of selected piece
     */
    void setSelectedPiece(int x, int y) {
        if (selectedPiece == null) {
            selectedPiece = board.getPiece(x, y);
        } else if (x == selectedPiece.getX() && y == selectedPiece.getY()) {
            selectedPiece = null;
        } else {
            selectedPiece = board.getPiece(x, y);
        }
    }

    /**
     * Investigates if you're about to move your piece to an upgrade position
     * @param x x coordinate of the position to which you want to move your piece
     * @param y y coordinate of the position to which you want to move your piece
     * @return true if you're about to move your piece to an upgrade position
     */
    boolean isInUpgradePosition(int x, int y) {
        if (selectedPiece.getUpgrades() == null) return false; // TODO: Båda dessa borde inte vara aktiva samtidigt?
        //System.out.println(board.getPiece(selectedX,selectedY).getUpgrades().size());
        if (selectedPiece.getUpgrades().size() == 0) return false;
        List<Upgrade> upgradeList = selectedPiece.getUpgrades(); // TODO: Båda dessa borde inte vara aktiva samtidigt? Eller?

        for (Upgrade upgrade : upgradeList) {
            if (upgrade.getX() == x && upgrade.getY() == y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Moves your piece to given position
     * @param x x coordinate where you want to put your piece
     * @param y y coordinate where you want to put your piece
     * @return true if possible, otherwise false
     */

    boolean movePiece(int x, int y) {
        if(selectedPiece.getColor() == currentPlayer && activatePieceAction(x, y)) {
            currentPlayer = getOpponentColor();
            return true;
        }
        return false;
    }

    /**
     * Activates an action for a piece, whether that is a move, capture or moveAndCapture
     * @param x position we want to move to
     * @param y position we want to move to
     * @return if able to move to (x,y)
     */
    private boolean activatePieceAction(int x, int y) { // CANTMOVE - when a piece is unable to move due to another piece

        int selectedX = selectedPiece.getX();
        int selectedY = selectedPiece.getY();

        List<PossibleMoveData> moves = board.getPossibleMoves(selectedX, selectedY);    //fetches the possible moves from the selected piece
        PossibleMoveData selected = null;
        for (PossibleMoveData pm:moves) {
            if(pm.x==x&&pm.y==y){          //checks if the possible moves contains (x,y)
                selected=pm;
                break;
            }
        }
        if(selected==null){                 //if it doesn't we cant move there
            return false;
        }
        switch (selected.possibleMove) {   //switch to see how we move there
            case MOVE:
                board.movePiece(selectedX, selectedY, x, y);
                updateGameState();
                return true;

            case CANTMOVE:
                return false;               //todo is this really necessary?

            case MOVEANDCAPTURE:
                board.moveAndCapturePiece(selectedX, selectedY, x, y);
                updateGameState();
                return true;

            case CAPTURE:
                board.capturePiece(x, y);
                updateGameState();
                return true;

            case UPGRADE:
                return true;
        }
        return false;
    }

    /**
     * Upgrades piece in this position to given token (opponentColor bcs current player has already been changed)
     */
    void upgradePiece(String upgrade, int x, int y) {
        board.upgradePiece(upgrade, x, y, getOpponentColor());
    }

    List<PossibleMoveData> getPossibleMoves() {
        if (selectedPiece == null) {
            return new ArrayList<>();
        }
        return board.getPossibleMoves(selectedPiece.getX(), selectedPiece.getY());
    }

    CustomPair[][] getBoardState() {
        return board.getBoardState();
    }

    Piece getPiece(int x, int y) {
        return board.getPiece(x, y);
    }

    private void updateGameState() {
        board.updateBoardState();
    }

    List<String> getCapturedBlackPieces() {
        return new ArrayList<>(board.getCapturedBlackPieces());
    }

    List<String> getCapturedWhitePieces() {
        return new ArrayList<>(board.getCapturedWhitePieces());
    }

    Board getBoard() {
        return board;
    }

    private PlayerColor getOpponentColor() {
        if (currentPlayer == PlayerColor.WHITE) return PlayerColor.BLACK;
        return PlayerColor.WHITE;
    }

    PlayerColor getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * A selected pieces' list of possible upgrades, intended for frontend use
     * @return list with possible pieces a selected piece can upgrade to converted to strings
     */
    List<String> getPossibleUpgradesList(int x, int y) {
        List<Upgrade> upgradeList = board.getPiece(x, y).getUpgrades();
        List<String> upgradeListToString = new ArrayList<>();
        for (Upgrade upgrade : upgradeList) {
            if (!upgradeListToString.contains(upgrade.getName())) {
                upgradeListToString.add(upgrade.getName());
            }

        }
        return upgradeListToString;
    }

}

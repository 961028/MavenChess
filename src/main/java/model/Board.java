package model;

import java.util.ArrayList;
import java.util.List;

class Board {

    private int width; // static?
    private int height;

    private Piece[][] board;
    private PlayerColor[][] boardColors;
    private String[][] boardNames;
    private CustomPair[][] boardState;

    private List<String> capturedWhitePieces = new ArrayList<>();
    private List<String> capturedBlackPieces = new ArrayList<>();

    Board(GameSettings settings) {
        this.width = settings.getWidth();
        this.height = settings.getHeight();
        board = settings.getBoard();
        boardState = new CustomPair[width][height];
        updateBoardState();
    }

    /**
     * Checks which moves are possible for a perticular piece
     *
     * @param x X position for the Piece
     * @param y Y position for the Piece
     * @return The possible moves
     */
    List<PossibleMoveData> getPossibleMoves(int x, int y) {

            int boardWidth = this.getWidth();
            int boardHeight = this.getHeight();

            List<PossibleMoveData> allMoves = new ArrayList<PossibleMoveData>();

            if (board[x][y].getMovements() != null) {
                addMovementPattern(x,y,allMoves, board[x][y].getMovements(), PossibleMove.MOVE, null);                                    //adds move tokens
                addMovementPattern(x,y,allMoves, board[x][y].getCaptures(), PossibleMove.CAPTURE, board[x][y].getOpponentColor());                  //adds capture tokens
                addMovementPattern(x,y,allMoves, board[x][y].getMoveAndCapture(), PossibleMove.MOVEANDCAPTURE, board[x][y].getOpponentColor());     //adds move and capture tokens
                addMovementPattern(x,y,allMoves, board[x][y].getMovements(), PossibleMove.BLOCKEDMOVE, board[x][y].getColor());                     //adds blocked move tokens
                //todo add check own pieces
            }
            return allMoves;

    }

    /**
     * mutates a list with moves for a piece to add a movepattern onto it, and adds a token to show how you can move somewhere
     * @param x position for piece we are checking
     * @param y position for piece we are checking
     * @param moves The full list of movements
     * @param pattern The movePattern we want to add
     * @param type  the type of token we will check
     * @param restriction if we only can place a token if it is a certain color
     */
    private void addMovementPattern(int x,int y,List<PossibleMoveData> moves, List<Movement> pattern, PossibleMove type, PlayerColor restriction) {

        //todo denna kanske ska kolla alla saker samtidigt?

        int boardWidth = this.getWidth();
        int boardHeight = this.getHeight();

        boolean[][] fullPattern = new boolean[boardWidth][boardHeight];
        if (pattern == null) return;

        for (Movement move : pattern) {
            move.addMovementPattern(x, y, board[x][y].getColor(), this, fullPattern);
        }

        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                if (fullPattern[i][j] && this.getPieceColor(i, j) == restriction) {
                    moves.add(new PossibleMoveData(i,j,type));
                }
            }
        }
    }

    /**
     * Upgrades and produces a new piece to put on current position
     * @param upgrade name of the piece to upgrade
     * @param x x coordinate of the piece to upgrade
     * @param y y coordinate of the piece to upgrade
     * @param color which color the upgraded piece belongs to
     */
    void upgradePiece(String upgrade, int x, int y, PlayerColor color) {
        Piece piece;
        switch (upgrade) {
            case "queen":
                piece = PieceFactory.queen(x, y, color); break;
            case "bishop":
                piece = PieceFactory.bishop(x, y, color); break;
            case "knight":
                piece = PieceFactory.knight(x, y, color); break;
            case "rook":
                piece = PieceFactory.rook(x, y, color); break;
            default:
                piece = PieceFactory.generalPiece(upgrade, x, y, color, null, null, null, null);
                // TODO fixa så att man kan göra det här modulärt
        }
        board[x][y] = piece;
    }


    void moveAndCapturePiece(int currentX, int currentY, int newX, int newY) {
        capturePiece(newX, newY);
        movePiece(currentX, currentY, newX, newY);
    }


    void capturePiece(int x, int y) {
        Piece capturedPiece = board[x][y];
        PlayerColor capturedPieceColor = capturedPiece.getColor();

        board[x][y] = null;

        if (capturedPieceColor == PlayerColor.WHITE) {
            capturedWhitePieces.add(capturedPiece.getName());
        } else {
            capturedBlackPieces.add(capturedPiece.getName());
        }
    }

    void movePiece(int currentX, int currentY, int newX, int newY) {
        board[newX][newY] = board[currentX][currentY];
        board[currentX][currentY] = null;
        board[newX][newY].updatePosition(newX, newY);
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    // Denna ska bort imo
    Piece getPiece(int checkPosX, int checkPosY) {
        Piece piece = board[checkPosX][checkPosY];
        return piece;
    }

    PlayerColor getPieceColor(int x, int y) {
        if (board[x][y] == null) return null;
        return board[x][y].getColor();
    }

    /**
     * Updates the boards state, so that frontend can get color and names on the right spots.
     */
    void updateBoardState() { // Kanske får ett error här pga boardstate aldrig initieras

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Piece currentPiece = board[x][y];
                //boardState[x][y] = null;                              //cleara position
                if(currentPiece != null) {                          // TODO Denna och getPieceColor kanske fixas av att lägga till NONE som spelarfärg? Så slipper vi != null skiten.
                    boardState[x][y] = board[x][y].getState();      //sätt nytt värde på position
                }
            }
        }
    }

    CustomPair[][] getBoardState() {
        return boardState;
    }

    List<String> getCapturedBlackPieces() {
        return capturedBlackPieces;
    }

    List<String> getCapturedWhitePieces() {
        return capturedWhitePieces;
    }

    Piece[][] getBoard() {
        return board;
    }
}

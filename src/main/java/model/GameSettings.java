package model;

class GameSettings {

    private int width;
    private int height;

    private Piece[][] board;

    GameSettings(int width, int height) {
        this.height = height;
        this.width = width;
        board = new Piece[width][height];
    }

    /**
     * populates the board according to classic chess rules
     */
    void populateClassicChess() {
        placePiece(PieceFactory.rook(0, 7, PlayerColor.WHITE));
        placePiece(PieceFactory.knight(1, 7, PlayerColor.WHITE));
        placePiece(PieceFactory.bishop(2, 7, PlayerColor.WHITE));
        placePiece(PieceFactory.queen(3, 7, PlayerColor.WHITE));
        placePiece(PieceFactory.king(4, 7, PlayerColor.WHITE));
        placePiece(PieceFactory.bishop(5,7, PlayerColor.WHITE));
        placePiece(PieceFactory.knight(6, 7, PlayerColor.WHITE));
        placePiece(PieceFactory.rook(7, 7, PlayerColor.WHITE));

        for (int x = 0; x < 8; x++) {
            addPawn(x, 6, PlayerColor.WHITE);
        }

        placePiece(PieceFactory.rook(0, 0, PlayerColor.BLACK));
        placePiece(PieceFactory.knight(1, 0, PlayerColor.BLACK));
        placePiece(PieceFactory.bishop(2, 0, PlayerColor.BLACK));
        placePiece(PieceFactory.queen(3, 0, PlayerColor.BLACK));
        placePiece(PieceFactory.king(4, 0, PlayerColor.BLACK));
        placePiece(PieceFactory.bishop(5,0, PlayerColor.BLACK));
        placePiece(PieceFactory.knight(6, 0, PlayerColor.BLACK));
        placePiece(PieceFactory.rook(7, 0, PlayerColor.BLACK));

        for (int x = 0; x < 8; x++) {
            addPawn(x, 1, PlayerColor.BLACK);
        }
    }

    /**
     * adds pawn to assigned place
     * @param x where to place pawn in matrix, x coordinate
     * @param y where to place pawn in matrix, y coordinate
     * @param color given color of piece
     */
    private void addPawn(int x, int y, PlayerColor color) {
        placePiece(PieceFactory.pawn(x,y,color));
    }

    /**
     * given a piece, its placed to given spot in matrix
     * @param piece
     */
    private void placePiece(Piece piece) {
        board[piece.getX()][piece.getY()] = piece;
    }

    Piece[][] getBoard() {
        return board;
    }

    void addPiece(int x, int y, Piece piece) {
        board[x][y] = piece;
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }
}

package model;

class Movement {

    private int xDistance, yDistance;
    private boolean isRecursive;

    /**
     * A Movement is a pattern/calculation that highlights spaces of a matrix as true if they are included in the pattern.
     * The x and y parameters decide which spaces (away from the xPos and yPos of the method addMovementPattern) to highlight.
     * The difference between the move of a king and queen would be that a queen's is recursive.
     *
     * @param x The distance away from a starting position to be highlighted, horizontally.
     * @param y The distance away from a starting position to be highlighted, vertically.
     * @param isRecursive If true, the calculation will run until finding another piece or hitting the edges of the board.
     */

    Movement(int x, int y, boolean isRecursive) {
        xDistance = x;
        yDistance = y;
        this.isRecursive = isRecursive;
    }

    Movement(int x, int y) {
        xDistance = x;
        yDistance = y;
        this.isRecursive = false;
    }

    /**
     * Takes a movePattern and highlights one or more positions with true.
     * Positions to be highlighted depend on the specifics defined when creating the Movement object,
     * as well as the input x and y positions.
     * The calculations also depend on the state of the board.
     *
     * @param posX The x position to start the calculation from.
     * @param posY The y position to start the calculation from.
     * @param color The color of the piece, used to inverse the direction of movement for one of the players.
     * @param board This should probably be a GameState or something instead.
     * @param movePattern The matrix to highlight calculated positions to.
     */

    void addMovementPattern(int posX, int posY, PlayerColor color, Board board, boolean[][] movePattern) { //TODO majoriteten av denna kanske borde vara i brädet så vi slipper ha beroende på brädet här

        int tempXDistance = xDistance;
        int tempYDistance = yDistance;

        if (color == PlayerColor.WHITE) {
            tempXDistance = -tempXDistance;
            tempYDistance = -tempYDistance;
        }


        int checkX = posX;
        int checkY = posY;
        boolean endLoop = false;

        while (checkIsInside(checkX, checkY, board.getWidth(), board.getHeight()) && !endLoop) {
            checkX += tempXDistance;
            checkY += tempYDistance;

            Piece checkPiece = null;
            if(checkIsInside(checkX, checkY, board.getWidth(), board.getHeight())) {
                checkPiece = board.getPiece(checkX, checkY);
                movePattern[checkX][checkY] = true;
            }

            if (checkPiece != null) endLoop = true;

            if (!isRecursive) endLoop = true;
        }
    }

    /**
     * Help method to check if an expected move can be done without escaping from the board
     * @param checkPosX x coordinate for position that you want to check
     * @param checkPosY y coordinate for position that you want to check
     * @param boardWidth width of the board
     * @param boardHeight height of the board
     * @return if it is inside
     */

    private boolean checkIsInside(int checkPosX, int checkPosY, int boardWidth, int boardHeight) {
        return (checkPosX < boardWidth && checkPosY < boardHeight) && (checkPosX >= 0 && checkPosY >= 0);
    }

}

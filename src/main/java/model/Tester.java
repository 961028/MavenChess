package model;


/** Contains tests **/

public class Tester {
    /*

    @Test
    public void placingTestPawn(){
        GameSettings gameSettings = new GameSettings(8,8);

        Piece testPiece = PieceFactory.pawn(2,1, PlayerColor.WHITE);

        gameSettings.addPiece(2, 1, testPiece); // places piece at 3,2 (zero index!)

        ChessGame game = new ChessGame(gameSettings);

        game.getPiece(2, 1);

        assertSame(game.getPiece(2, 1), testPiece);

        game.setSelectedPiece(2, 1);

        game.activatePieceAction(2,3); // moving piece on 2,1 two steps forward

        assertSame(game.getPiece(2,3), testPiece);
    }

 */

    /**
     * classic pieces in right spots test - WORKS // ok nvm it doesn't
     *
     */
    /*
   @Test
   public void placeAllPiecesClassicChess() {
        int counter = 0;
        Model model = new Model();
        model.startNewGame("classic");
        model.getGame().getBoard().getBoard();

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (model.getGame().getPiece(x,y) != null) {
                    counter++;
                    //System.out.println(model.getGame().getPiece(x,y).getX());
                    //System.out.println(model.getGame().getPiece(x,y).getY());
                    //System.out.println(model.getGame().getPiece(x, y).getName());
                    //System.out.println(model.getGame().getPiece(x, y).getColor());
                    //System.out.println();
                }
            }
        }
       //System.out.println(counter);
   }

     */
    /**

     * pawn capturing a queen test
     */

    /*
    @Test
    public void pawnCapture() {
        GameSettings settings = new GameSettings(8, 8);

        Piece testPiecePawn = PieceFactory.pawn(2,1, PlayerColor.WHITE);
        Piece testPieceQueen = PieceFactory.queen(3,2, PlayerColor.BLACK);

        settings.addPiece(2, 1, testPiecePawn);
        settings.addPiece(3, 2, testPieceQueen);

        ChessGame game = new ChessGame(settings);

        assertSame(game.getPiece(2, 1), testPiecePawn);
        assertSame(game.getPiece(3, 2), testPieceQueen);

        game.setSelectedPiece(2, 1);
        game.activatePieceAction(3, 2);


        assertSame(game.getPiece(3,2), testPiecePawn);

        game.getPiece(3,2);
    }

    @Test
    public void bishopCapture() {
        GameSettings settings = new GameSettings(8, 8);

        Piece testPiecePawn = PieceFactory.pawn(6,5, PlayerColor.BLACK);
        Piece testPieceBishop = PieceFactory.bishop(2,1, PlayerColor.WHITE);

        settings.addPiece(testPiecePawn.getX(), testPiecePawn.getY(), testPiecePawn);
        settings.addPiece(testPieceBishop.getX(), testPieceBishop.getY(), testPieceBishop);

        ChessGame game = new ChessGame(settings);

        game.setSelectedPiece(2,1);
        game.activatePieceAction(6,5);

        assertSame(game.getPiece(6,5), testPieceBishop);
    }

     */

    /*
    @Test
    public void moveAndRemove() {
        GameSettings settings = new GameSettings(8, 8);

        Piece testPiecePawn = PieceFactory.pawn(1,1, PlayerColor.WHITE);


        settings.addPiece(testPiecePawn.getX(), testPiecePawn.getY(), testPiecePawn);
        //settings.addPiece(testPieceBishop.getX(), testPieceBishop.getY(), testPieceBishop);

        ChessGame game = new ChessGame(settings);

        game.setSelectedPiece(2,1);
        game.activatePieceAction(6,5);

        assertSame(game.getPiece(6,5), testPieceBishop);
    }

     */

}

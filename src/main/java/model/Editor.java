package model;

public class Editor {

    String gameName;
    GameSettings gameSettings;

    void createNewGameSetting(String name, int x, int y) {
        gameName = name;
        gameSettings = new GameSettings(x, y);
    }

    void createPiece(String name) {

    }

    void addPieceToBoard(String name, int x, int y) {
        gameSettings.addPiece(x, y, getPieceFromName(name));
    }

    private Piece getPieceFromName(String name) {
        return null;
    }
}

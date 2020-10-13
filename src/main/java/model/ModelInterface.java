package model;

import java.util.List;

public interface ModelInterface {
    void startNewGame(String settings);

    void loadGame(ChessGame game);

    void movePiece(int x, int y);

    List<String> getCapturedBlackPieces();

    List<String> getCapturedWhitePieces();

    void selectPiece(int x, int y);

    void selectedUpgrade(String upgrade, int x, int y);
}

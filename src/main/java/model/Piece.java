package model;

import java.util.List;

class Piece {

    private String name;
    private int x, y;
    private PlayerColor color;
    private PlayerColor opponentColor;
    private List<Movement> movements;
    private List<Movement> moveAndCapture;
    private List<Movement> captures;
    private List<SpecialRule> specialRules;
    private List<Upgrade> upgrades;

    //todo startposition, upgrade

    //ex. super-archer-queen without upgrade
    Piece(String name, List<Movement> movements, List<Movement> moveAndCapture, List<Movement> captures, int x, int y, PlayerColor color, List<SpecialRule> specialRules) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.movements = movements;
        this.moveAndCapture = moveAndCapture;
        this.captures = captures;
        this.color = color;
        this.specialRules = specialRules;
        this.opponentColor = getOpponentColor();
    }


    //TODO dessa ska väl bort egentligen? vi har väl factoryn för att göra alla dessa ändå.
    // ex. archer
    Piece(String name, List<Movement> movements, List<Movement> moveAndCapture, List<Movement> captures, int x, int y, PlayerColor color) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.movements = movements;
        this.moveAndCapture = moveAndCapture;
        this.color = color;
        this.opponentColor = getOpponentColor();
    }

    //pawn without upgrade
    Piece(String name, List<Movement> movements, List<Movement> moveAndCapture, int x, int y, PlayerColor color, List<SpecialRule> specialRules) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.movements = movements;
        this.moveAndCapture = moveAndCapture;
        this.color = color;
        this.specialRules = specialRules;
        this.opponentColor = getOpponentColor();
    }

    //pawn without upgrade
    Piece(String name, List<Movement> movements, List<Movement> moveAndCapture, int x, int y, PlayerColor color, List<SpecialRule> specialRules, List<Upgrade> upgrades) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.movements = movements;
        this.moveAndCapture = moveAndCapture;
        this.color = color;
        this.specialRules = specialRules;
        this.upgrades = upgrades;
        this.opponentColor = getOpponentColor();
    }


    //ex. bishop
    Piece(String name, List<Movement> movements, List<Movement> moveAndCapture, int x, int y, PlayerColor color) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.movements = movements;
        this.moveAndCapture = moveAndCapture;
        this.color = color;
        this.opponentColor = getOpponentColor();
    }



    /**
     * investigates if the piece is able to capture any pieces (prevents possible movements to be overwritten)
     * @param moveAndCapturePattern matrix containing enum MOVEANDCAPTURE on positions where possible to capture piece os opposite color
     * @return true if any piece is able to capture, otherwise false
     */

    //todo jag kan ha nukat dennas funktionali
    private boolean isAbleToMoveAndCapture(List<PossibleMoveData> moveAndCapturePattern) {
        if(moveAndCapturePattern.size()!=0){
            return true;
        }
        return false;
    }

    void updatePosition(int newX, int newY) {
        x = newX;
        y = newY;
    }

    PlayerColor getColor() {
        return color;
    }

    String getName() {
        return name;
    }

    CustomPair getState() {
        return new CustomPair(getColor(), getName());
    }

    PlayerColor getOpponentColor() {
        if (color == PlayerColor.WHITE) return PlayerColor.BLACK;
        return PlayerColor.WHITE;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    List<Movement> getMovements() {
        return movements;
    }

    List<Movement> getMoveAndCapture() {
        return moveAndCapture;
    }

    List<Movement> getCaptures() {
        return captures;
    }

    List<SpecialRule> getSpecialRules() {
        return specialRules;
    }

    List<Upgrade> getUpgrades() {
        return upgrades;
    }
}

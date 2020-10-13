package model;

import java.util.ArrayList;
import java.util.List;

class PieceFactory {

    static Piece king(int x, int y, PlayerColor color) {
        Movement movement1 = new Movement(-1, -1);
        Movement movement2 = new Movement(-1, 0);
        Movement movement3 = new Movement(-1, 1);
        Movement movement4 = new Movement(0, -1);
        Movement movement5 = new Movement(0, 1);
        Movement movement6 = new Movement(1, -1);
        Movement movement7 = new Movement(1, 0);
        Movement movement8 = new Movement(1, 1);
        List<Movement> movements = new ArrayList<>();
        movements.add(movement1);
        movements.add(movement2);
        movements.add(movement3);
        movements.add(movement4);
        movements.add(movement5);
        movements.add(movement6);
        movements.add(movement7);
        movements.add(movement8);

        List<Movement> moveandcapture = new ArrayList<>();
        for (Movement move : movements) {
            moveandcapture.add(move);
        }

        SpecialRule king = new SpecialRule(true);
        List<SpecialRule> kingRuleStuff = new ArrayList<>();
        kingRuleStuff.add(king);


        return new Piece("king", movements, moveandcapture, x, y, color, kingRuleStuff);
    }

    static Piece queen(int x, int y, PlayerColor color) {
        Movement movement1 = new Movement(-1,0, true);
        Movement movement2 = new Movement(0,-1, true);
        Movement movement3 = new Movement(1,0, true);
        Movement movement4 = new Movement(0,1, true);
        Movement movement5 = new Movement(-1,-1, true);
        Movement movement6 = new Movement(-1,1, true);
        Movement movement7 = new Movement(1,-1, true);
        Movement movement8 = new Movement(1,1, true);
        List<Movement> movements = new ArrayList<>();
        movements.add(movement1);
        movements.add(movement2);
        movements.add(movement3);
        movements.add(movement4);
        movements.add(movement5);
        movements.add(movement6);
        movements.add(movement7);
        movements.add(movement8);

        List<Movement> moveandcapturelist = new ArrayList<>();
        for (Movement move : movements) {
            moveandcapturelist.add(move);
        }

        return new Piece("queen", movements, moveandcapturelist, x, y, color);
    }



    static Piece bishop(int x, int y, PlayerColor color) { // löpare
        Movement movement1 = new Movement(-1,-1, true);
        Movement movement2 = new Movement(-1,1, true);
        Movement movement3 = new Movement(1,-1, true);
        Movement movement4 = new Movement(1,1, true);
        List<Movement> movements = new ArrayList<>();
        movements.add(movement1);
        movements.add(movement2);
        movements.add(movement3);
        movements.add(movement4);

        List<Movement> movementandcapturelist = new ArrayList<>();
        movementandcapturelist.add(movement1);
        movementandcapturelist.add(movement2);
        movementandcapturelist.add(movement3);
        movementandcapturelist.add(movement4);

        return new Piece("bishop", movements, movementandcapturelist, x, y, color);
    }



    static Piece knight(int x, int y, PlayerColor color) { // häst
        Movement movement1 = new Movement(-1,2);
        Movement movement2 = new Movement(1,2);
        Movement movement3 = new Movement(-2,1);
        Movement movement4 = new Movement(-2,-1);
        Movement movement5 = new Movement(-1,-2);
        Movement movement6 = new Movement(1,-2);
        Movement movement7 = new Movement(2,1);
        Movement movement8 = new Movement(2,-1);
        List<Movement> movements = new ArrayList<>();
        movements.add(movement1);
        movements.add(movement2);
        movements.add(movement3);
        movements.add(movement4);
        movements.add(movement5);
        movements.add(movement6);
        movements.add(movement7);
        movements.add(movement8);

        List<Movement> movementandcapturelist = new ArrayList<>();
        movementandcapturelist.add(movement1);
        movementandcapturelist.add(movement2);
        movementandcapturelist.add(movement3);
        movementandcapturelist.add(movement4);
        movementandcapturelist.add(movement5);
        movementandcapturelist.add(movement6);
        movementandcapturelist.add(movement7);
        movementandcapturelist.add(movement8);

        return new Piece("knight", movements, movementandcapturelist, x, y, color);
    }



    static Piece rook(int x, int y, PlayerColor color) { // torn
        Movement movement1 = new Movement(0,1,true);
        Movement movement2 = new Movement(1,0,true);
        List<Movement> movements = new ArrayList<>();
        movements.add(movement1);
        movements.add(movement2);

        Movement moveandcapture1 = new Movement(0,1);
        Movement moveandcapture2 = new Movement(1,0);
        List<Movement> moveandcapturelist = new ArrayList<>();
        moveandcapturelist.add(moveandcapture1);
        moveandcapturelist.add(moveandcapture2);

        return new Piece("rook", movements, moveandcapturelist, x, y, color);
    }



    static Piece pawn(int x, int y, PlayerColor color) { // bonde
        Movement moveandcapture1 = new Movement(1,1);
        Movement moveandcapture2 = new Movement(-1,1);

        List<Movement> moveandcaptures = new ArrayList<>();
        moveandcaptures.add(moveandcapture1);
        moveandcaptures.add(moveandcapture2);

        Movement movement1 = new Movement(0, 2);
        Movement movement2 = new Movement(0, 1);


        List<Movement> movements = new ArrayList<>();
        movements.add(movement1);
        movements.add(movement2);

        SpecialRule specialRule1 = new SpecialRule(0);
        List<SpecialRule> specialRules = new ArrayList<>();
        specialRules.add(specialRule1);

        List<Upgrade> upgrades = new ArrayList<>();
        for (int width = 0; width < 8; width++) {
            int height = 0;
            if (y == 1) {
                height = 7;
            } else if (y == 6) {
                height = 0;
            }
            upgrades.add(new Upgrade(width, height, "queen"));
        }

        for (int width = 0; width < 8; width++) {
            int height = 0;
            if (y == 1) {
                height = 7;
            } else if (y == 6) {
                height = 0;
            }
            upgrades.add(new Upgrade(width, height, "bishop"));
        }

        for (int width = 0; width < 8; width++) {
            int height = 0;
            if (y == 1) {
                height = 7;
            } else if (y == 6) {
                height = 0;
            }
            upgrades.add(new Upgrade(width, height, "rook"));
        }

        for (int width = 0; width < 8; width++) {
            int height = 0;
            if (y == 1) {
                height = 7;
            } else if (y == 6) {
                height = 0;
            }
            upgrades.add(new Upgrade(width, height, "knight"));
        }

        Piece pawn = new Piece("pawn", movements, moveandcaptures, x, y, color, specialRules, upgrades);

        return pawn;
    }

    static Piece generalPiece(String name, int x, int y, PlayerColor color, List<Movement> movements, List<Movement> captures, List<Movement> moveandcaptures, List<SpecialRule> specialRules) {
        Piece piece;
        if (captures != null) {
            piece = new Piece(name, movements, captures, moveandcaptures, x, y, color, specialRules);
        } else {
            piece = new Piece(name, movements, moveandcaptures, x, y, color, specialRules);
        }

        return piece;
    }


}

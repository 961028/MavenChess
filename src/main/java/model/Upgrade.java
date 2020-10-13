package model;

/**
 *  component to piece
 */
class Upgrade {

    private String name;
    private int x;
    private int y;

    /**
     * Defines where and to what a certain piece upgrades to
     *
     * @param x x coordinate of the pieces' upgrade position
     * @param y y coordinate of the pieces' upgrade position
     * @param name name of the token a piece can upgrade to
     */

    Upgrade(int x, int y, String name) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    String getName() {
        return name;
    }
}

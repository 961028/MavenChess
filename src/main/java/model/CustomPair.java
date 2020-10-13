package model;

public class CustomPair {
    PlayerColor color;
    String name;

    CustomPair (PlayerColor color, String name) {
        this.color = color;
        this.name = name;
    }

    public PlayerColor getKey() {
        return color;
    }

    public String getValue() {
        return name;
    }
}

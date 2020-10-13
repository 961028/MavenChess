package model;

class SpecialRule {
    private int counter;
    private boolean isKing = false;

    SpecialRule(int counter) {
        this.counter = counter;
    }

    SpecialRule(boolean isKing) {
        this.isKing = isKing;
    }

    void kingStuff() {
        if (isKing) {

        }
    }

    int getCounter() {
        return counter;
    }
}

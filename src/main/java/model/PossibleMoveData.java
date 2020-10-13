package model;

public class PossibleMoveData {
    public int x;
    public int y;

    public PossibleMove possibleMove;

    public PossibleMoveData(int x,int y,PossibleMove p){
        this.x=x;
        this.y=y;
        this.possibleMove=p;
    }
}

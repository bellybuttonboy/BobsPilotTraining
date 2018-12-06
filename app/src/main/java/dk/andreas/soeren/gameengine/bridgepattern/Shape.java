package dk.andreas.soeren.gameengine.bridgepattern;

public abstract class Shape {
    protected Color color;

    public Shape(Color c){
        this.color=c;
    }

    abstract public void applyColor();
}

package dk.andreas.soeren.gameengine.bridgepattern;

public class RedColor implements Color{
    @Override
    public void applyColor() {
        System.out.println("red.");
    }
}

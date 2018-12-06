package dk.andreas.soeren.gameengine.bridgepattern;

public class BridgePatternTest {
    public static void main(String[] args) {
        Shape triangle = new Triangle(new RedColor());
        triangle.applyColor();

        Shape pentagon = new Pentagon(new GreenColor());
        pentagon.applyColor();

        Shape pentagon1 = new Pentagon(new RedColor());
        pentagon1.applyColor();

        pentagon1 = new Triangle(new RedColor());
        pentagon1.applyColor();


    }
}

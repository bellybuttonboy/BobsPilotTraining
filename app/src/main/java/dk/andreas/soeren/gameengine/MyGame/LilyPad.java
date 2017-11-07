package dk.andreas.soeren.gameengine.MyGame;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Andreas on 13-Oct-17.
 */

public class LilyPad
{
    int x;
    int y;
    boolean frogOnPad = false;
    int frogComparisonY;

    public LilyPad(int x, int y, int frogComparisonY)
    {
        this.frogComparisonY = frogComparisonY;
        this.y = y;
        this.x = x;
    }
}

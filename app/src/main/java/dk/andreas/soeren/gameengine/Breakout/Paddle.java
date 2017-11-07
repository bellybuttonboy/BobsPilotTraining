package dk.andreas.soeren.gameengine.Breakout;

/**
 * Created by Andreas on 17-Oct-17.
 */

public class Paddle
{
    public static final float Width = 56;
    public static final float Height = 11;
    public int x = 160 - (int)Width/2;
    public int y = (int) World.MAX_Y - 30;


}

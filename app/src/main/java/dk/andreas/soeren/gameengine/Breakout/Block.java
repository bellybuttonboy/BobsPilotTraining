package dk.andreas.soeren.gameengine.Breakout;

/**
 * Created by Andreas on 17-Oct-17.
 */

public class Block
{
    public static final float WIDITH = 40;
    public static final float HEIGHT = 144/8;

    public float x;
    public float y;
    public int type;

    public Block (float x, float y, int type)
    {
        this.x = x;
        this.y = y;
        this.type = type;
    }
}

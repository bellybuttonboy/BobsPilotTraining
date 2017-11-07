package dk.andreas.soeren.gameengine.Breakout;

/**
 * Created by Andreas on 24-Oct-17.
 */

public interface CollisionListener
{
    public void collisionWall();
    public void collisionPaddle();
    public void collisionBlock();
    public void gameover();
}

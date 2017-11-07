package dk.andreas.soeren.gameengine.Carscroller;

/**
 * Created by Andreas on 24-Oct-17.
 */

public interface CollisionListener
{
    public void collisionWall();
    public void collisionMonster();
    public void gameover();
}

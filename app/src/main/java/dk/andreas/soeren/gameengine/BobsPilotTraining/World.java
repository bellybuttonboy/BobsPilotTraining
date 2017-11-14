package dk.andreas.soeren.gameengine.BobsPilotTraining;

import dk.andreas.soeren.gameengine.GameEngine;


public class World
{
    public static final float MIN_X = 0;
    public static final float MAX_X = 319;
    public static final float MIN_Y = 0;
    public static final float MAX_Y = 479;
    GameEngine gameEngine;
    //CollisionListener collisionListener;
    boolean gameOver = false;
    public Plane plane = null;

    public World(GameEngine gameEngine)
    {
        this.gameEngine = gameEngine;
        plane = new Plane();
    }


    public void update(float deltatime)
    {
        if (gameEngine.isTouchDown(0))
        {
            plane.previousX = plane.x;
            plane.previousY = plane.y;

            plane.x = gameEngine.getTouchX(0);
            plane.y = gameEngine.getTouchY(0);
        }
    }

    private boolean collideRectangles(float x1, float y1, float width1, float height1,
                                      float x2, float y2, float width2, float height2)
    {
        if (x1 < x2 + width2 &&
                x1 + width1 > x2 &&
                y1 + height1 > y2 &&
                y1 < y2 + height2)
        {
            return true;
        }
        return false;
    }
}


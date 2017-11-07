package dk.andreas.soeren.gameengine.MyGame;

import android.util.Log;

import dk.andreas.soeren.gameengine.GameEngine;
import dk.andreas.soeren.gameengine.Screen;

public class GameScreen extends Screen
{
    WorldRenderer worldRenderer;
    World world;
    boolean fingerLifted = true;

    public GameScreen(GameEngine gameEngine)
    {
        super(gameEngine);
        world = new World();
        worldRenderer = new WorldRenderer(gameEngine, world);
    }

    @Override
    public void update(float deltaTime)
    {
        String direction = "";
        if (!gameEngine.isTouchDown(0))
        {
            fingerLifted = true;
        }
        else if (gameEngine.isTouchDown(0) && fingerLifted)
        {
            if (gameEngine.getTouchY(0) > gameEngine.getFrameBufferHeight() / 2)
            {
                direction = "down";
            }
            else if (gameEngine.getTouchX(0) > gameEngine.getFrameBufferWidth() / 2)
            {
                direction = "right";
            }
            else if (gameEngine.getTouchX(0) < gameEngine.getFrameBufferWidth() / 2)
            {
                direction = "left";
            }
            fingerLifted = false;
        }
        world.update(deltaTime, direction);
        worldRenderer.render();
    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void dispose()
    {

    }
}

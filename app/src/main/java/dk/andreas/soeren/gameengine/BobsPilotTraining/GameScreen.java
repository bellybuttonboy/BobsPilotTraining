package dk.andreas.soeren.gameengine.BobsPilotTraining;

import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;

import dk.andreas.soeren.gameengine.GameEngine;
import dk.andreas.soeren.gameengine.Screen;

public class GameScreen extends Screen
{
    WorldRenderer renderer = null;
    World world = null;
    Typeface font = null;

    public GameScreen(GameEngine gameEngine)
    {
        super(gameEngine);
        world = new World(gameEngine);
        renderer = new WorldRenderer(gameEngine, world);
    }

    @Override
    public void update(float deltaTime)
    {
        world.update(deltaTime);
        renderer.render(deltaTime);
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

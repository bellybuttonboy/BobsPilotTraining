package dk.andreas.soeren.gameengine.BobsPilotTraining;

import android.graphics.Bitmap;

import dk.andreas.soeren.gameengine.GameEngine;
import dk.andreas.soeren.gameengine.Screen;

/**
 * Created by Andreas on 07-Nov-17.
 */

public class MainMenuScreen extends Screen
{
    Bitmap background = null;
    public MainMenuScreen(GameEngine gameEngine)
    {
        super(gameEngine);
        background = gameEngine.loadBitMap("bobspilottrainingassets/joyofmspaint.png");
    }

    @Override
    public void update(float deltaTime)
    {
        gameEngine.drawBitmap(background, 0, 0);
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

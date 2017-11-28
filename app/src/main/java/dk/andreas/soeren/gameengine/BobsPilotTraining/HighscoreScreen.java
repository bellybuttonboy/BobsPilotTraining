package dk.andreas.soeren.gameengine.BobsPilotTraining;

import android.graphics.Bitmap;

import dk.andreas.soeren.gameengine.GameEngine;
import dk.andreas.soeren.gameengine.Screen;

/**
 * Created by Andreas on 28-Nov-17.
 */

public class HighscoreScreen extends Screen
{
    String assetsFolder = "bobspilottrainingassets/";
    Bitmap background = null;
    Bitmap returnToMainMenuButton = null;
    public HighscoreScreen(GameEngine gameEngine)
    {
        super(gameEngine);
        background = gameEngine.loadBitMap(assetsFolder + "HighscoreScreen.png");
        returnToMainMenuButton = gameEngine.loadBitMap(assetsFolder + "returnToMainMenuButton.png");
    }

    @Override
    public void update(float deltaTime)
    {
        gameEngine.drawBitmap(background, 0, 0);
        gameEngine.drawBitmap(returnToMainMenuButton, 10, 250);



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

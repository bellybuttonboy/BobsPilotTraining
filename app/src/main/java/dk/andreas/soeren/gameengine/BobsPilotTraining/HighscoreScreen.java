package dk.andreas.soeren.gameengine.BobsPilotTraining;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;

import dk.andreas.soeren.gameengine.GameEngine;
import dk.andreas.soeren.gameengine.Screen;

/**
 * Created by Andreas on 28-Nov-17.
 */

public class HighscoreScreen extends Screen
{
    private String assetsFolder = "bobspilottrainingassets/";
    private Bitmap background = null;
    private Bitmap returnToMainMenuButton = null;
    private String[] highscoresArray;
    private Typeface font = null;

    public HighscoreScreen(GameEngine gameEngine)
    {
        super(gameEngine);
        background = gameEngine.loadBitMap(assetsFolder + "HighscoreScreen.png");
        returnToMainMenuButton = gameEngine.loadBitMap(assetsFolder + "returnToMainMenuButton.png");
        font = gameEngine.loadFont(assetsFolder + "Chewy-Regular.ttf");
        highscoresArray = gameEngine.getHighscores();
    }

    @Override
    public void update(float deltaTime)
    {
        gameEngine.drawBitmap(background, 0, 0);
        gameEngine.drawBitmap(returnToMainMenuButton, 10, 310);

        gameEngine.drawText(font,"1. : " + highscoresArray[0], 115, 160, Color.RED, 36);
        gameEngine.drawText(font, "2. : " + highscoresArray[1], 115, 210, Color.RED, 36);
        gameEngine.drawText(font, "3. : " + highscoresArray[2], 115, 260, Color.RED, 36);



        if (gameEngine.isTouchDown(0))
        {
            if (gameEngine.getTouchX(0) > 10 && gameEngine.getTouchX(0) < 310 &&
                    gameEngine.getTouchY(0) > 310 && gameEngine.getTouchY(0) < 360)
            {
                gameEngine.setScreen(new MainMenuScreen(gameEngine, false));
                return;
            }
        }
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

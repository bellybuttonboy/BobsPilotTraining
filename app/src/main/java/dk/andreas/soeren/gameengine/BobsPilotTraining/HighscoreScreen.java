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
    String assetsFolder = "bobspilottrainingassets/";
    Bitmap background = null;
    Bitmap returnToMainMenuButton = null;
    Highscores highscores;
    String[] highscoresArray;
    Typeface font = null;

    public HighscoreScreen(GameEngine gameEngine)
    {
        super(gameEngine);
        background = gameEngine.loadBitMap(assetsFolder + "HighscoreScreen.png");
        returnToMainMenuButton = gameEngine.loadBitMap(assetsFolder + "returnToMainMenuButton.png");
        font = gameEngine.loadFont(assetsFolder + "Chewy-Regular.ttf");
        highscores = new Highscores();
        highscores.loadRecords(gameEngine);
        highscoresArray = highscores.highscores;
    }

    @Override
    public void update(float deltaTime)
    {
        gameEngine.drawBitmap(background, 0, 0);
        gameEngine.drawBitmap(returnToMainMenuButton, 10, 310);

        gameEngine.drawText(font,"1. : " + highscoresArray[0], 100, 190, Color.RED, 36);
        gameEngine.drawText(font, "2. : " + highscoresArray[1], 100, 240, Color.RED, 36);
        gameEngine.drawText(font, "3. : " + highscoresArray[2], 100, 290, Color.RED, 36);



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

package dk.andreas.soeren.gameengine.BobsPilotTraining;

import android.graphics.Bitmap;

import java.util.List;

import dk.andreas.soeren.gameengine.GameEngine;
import dk.andreas.soeren.gameengine.Screen;
import dk.andreas.soeren.gameengine.TouchEvent;

/**
 * Created by Andreas on 07-Nov-17.
 */

public class MainMenuScreen extends Screen
{
    Bitmap background = null;
    Bitmap startgameButton = null;
    Bitmap recordButton = null;
    String assetsFolder = "bobspilottrainingassets/";
    boolean touchReleased = false;

    public MainMenuScreen(GameEngine gameEngine, boolean touchReleased)
    {
        super(gameEngine);
        background = gameEngine.loadBitMap( assetsFolder + "StartScreen.png");
        startgameButton = gameEngine.loadBitMap(assetsFolder + "playButton.png");
        recordButton = gameEngine.loadBitMap(assetsFolder + "recordButton.png");
        this.touchReleased = touchReleased;
    }

    @Override
    public void update(float deltaTime)
    {
        gameEngine.drawBitmap(background, 0, 0);
        gameEngine.drawBitmap(startgameButton, 10, 330);
        gameEngine.drawBitmap(recordButton, 10, 390);

        List<TouchEvent> events = gameEngine.getTouchEvents();

        int eventsSize = events.size();

        if(!touchReleased)
        {
            for (int i = 0; i < eventsSize; i++)
            {
                if (events.get(i).type == TouchEvent.TouchEventType.Up)
                {
                    touchReleased = true;
                }
            }
        }
        else
        {
            if (gameEngine.isTouchDown(0))
            {
                if (gameEngine.getTouchX(0) > 10 && gameEngine.getTouchX(0) < 310 &&
                        gameEngine.getTouchY(0) > 330 && gameEngine.getTouchY(0) < 380)
                {
                    gameEngine.setScreen(new GameScreen(gameEngine));
                    return;
                }
                else if (gameEngine.getTouchX(0) > 10 && gameEngine.getTouchX(0) < 310 &&
                        gameEngine.getTouchY(0) > 390 && gameEngine.getTouchY(0) < 440)
                {
                    gameEngine.setScreen(new HighscoreScreen(gameEngine));
                    return;
                }
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

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
        startgameButton = gameEngine.loadBitMap(assetsFolder + "playButton");
        recordButton = gameEngine.loadBitMap(assetsFolder + "recordButton");
        this.touchReleased = touchReleased;
    }

    @Override
    public void update(float deltaTime)
    {
        gameEngine.drawBitmap(background, 0, 0);

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
                gameEngine.setScreen(new GameScreen(gameEngine));
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

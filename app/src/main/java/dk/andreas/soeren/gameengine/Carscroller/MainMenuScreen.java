package dk.andreas.soeren.gameengine.Carscroller;

import android.graphics.Bitmap;

import dk.andreas.soeren.gameengine.GameEngine;
import dk.andreas.soeren.gameengine.Screen;

/**
 * Created by Andreas on 03-Oct-17.
 */

public class MainMenuScreen extends Screen
{
    Bitmap background = null;
    Bitmap startgame = null;
    float passedTime = 0;
    long startTime = 0;

    public MainMenuScreen(GameEngine gameEngine)
    {
        super(gameEngine);
        background = gameEngine.loadBitMap("carscrollerassets/xcarbackground.png");
        startgame = gameEngine.loadBitMap("carscrollerassets/xstartgame.png");

    }

    @Override
    public void update(float deltaTime)
    {
        if (gameEngine.isTouchDown(0))
        {
            gameEngine.setScreen(new GameScreen(gameEngine));
            return;
        }
        else
        {
            gameEngine.drawBitmap(background, 0, 0);
            passedTime = passedTime + deltaTime;
            if (passedTime - (int)passedTime > 0.5f)
            {
                gameEngine.drawBitmap(startgame, 240 - (startgame.getWidth()/2), 160 - startgame.getHeight());
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

package dk.andreas.soeren.gameengine.Breakout;

import android.graphics.Bitmap;

import dk.andreas.soeren.gameengine.GameEngine;
import dk.andreas.soeren.gameengine.Screen;

/**
 * Created by Andreas on 03-Oct-17.
 */

public class MainMenuScreen extends Screen
{
    Bitmap mainmenu = null;
    Bitmap insertCoin = null;
    float passedTime = 0;
    long startTime = 0;

    public MainMenuScreen(GameEngine gameEngine)
    {
        super(gameEngine);
        mainmenu = gameEngine.loadBitMap("breakoutassets/mainmenu.png");
        insertCoin = gameEngine.loadBitMap("breakoutassets/insertcoin.png");

    }

    @Override
    public void update(float deltaTime)
    {
        startTime = System.nanoTime();
        if (gameEngine.isTouchDown(0))
        {
            gameEngine.setScreen(new GameScreen(gameEngine));
            return;
        }
        else
        {
            gameEngine.drawBitmap(mainmenu, 0, 0);
            passedTime = passedTime + deltaTime;
            if (passedTime - (int)passedTime > 0.5f)
            {
                gameEngine.drawBitmap(insertCoin, 160 - (insertCoin.getWidth()/2), 360 - insertCoin.getHeight());
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

package dk.andreas.soeren.gameengine;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

import java.util.List;
import java.util.Random;

/**
 * Created by Søren on 05-09-2017.
 */

public class TestScreen extends Screen
{

    Bitmap bob = null;
    float bobX = 0;
    float bobY = 50;
    TouchEvent event = null;
    Sound sound = null;
    Music music = null;
    boolean isPlaying = false;

    public TestScreen(GameEngine gameEngine)
    {
        super(gameEngine);
        bob = gameEngine.loadBitMap("bob.png");
        sound = gameEngine.loadSound("blocksplosion.wav");
        music = gameEngine.loadMusic("music.ogg");
        music.setLooping(true);
        music.play();
        isPlaying = true;
    }
    @Override
    public void update(float deltaTime)
    {
        gameEngine.clearFrameBuffer(Color.BLUE);

        bobX = bobX + (1000 * deltaTime);
        if(bobX > gameEngine.getFrameBufferWidth())
        {
            bobX = 0 - bob.getWidth();
        }
        gameEngine.drawBitmap(bob,(int)bobX, (int)bobY);

    }

    @Override
    public void pause()
    {
        Log.d("TestScreen", "The SCREEN IS PAUSED");
        music.pause();
        isPlaying = false;
    }

    @Override
    public void resume()
    {
        Log.d("TestScreen", "The SCREEN IS resumed");
        if(!isPlaying)
        {
            music.play();
            isPlaying = true;
        }
    }

    @Override
    public void dispose()
    {
        Log.d("TestScreen", "The SCREEN IS disposed");
        music.dispose();
    }
}

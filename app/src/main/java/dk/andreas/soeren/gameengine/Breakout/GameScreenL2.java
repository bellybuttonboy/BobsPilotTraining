package dk.andreas.soeren.gameengine.Breakout;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;

import java.util.List;

import dk.andreas.soeren.gameengine.GameEngine;
import dk.andreas.soeren.gameengine.Screen;
import dk.andreas.soeren.gameengine.Sound;
import dk.andreas.soeren.gameengine.TouchEvent;

/**
 * Created by Andreas on 24-Oct-17.
 */
public class GameScreenL2 extends Screen
{
    enum State
    {
        Paused,
        Running,
        GameOver
    }
    WorldL2 world;
    WorldRenderer2 renderer;
    State state = State.Running;


    Bitmap background = null;
    Bitmap resume = null;
    Bitmap gameOver = null;
    Typeface font = null;
    Sound bounceSound = null;
    Sound blockSound = null;
    public Sound gameOverSound = null;

    public GameScreenL2(GameEngine gameEngine)
    {
        super(gameEngine);
        world = new WorldL2(gameEngine, new CollisionListener()
        {
            public void collisionWall()
            {
                bounceSound.play(1);
            }

            @Override
            public void collisionPaddle()
            {
                bounceSound.play(1);
            }

            @Override
            public void collisionBlock()
            {
                blockSound.play(1);
            }

            @Override
            public void gameover()
            {
                gameOverSound.play(1);
            }

        });
        renderer = new WorldRenderer2(gameEngine, world);
        background = gameEngine.loadBitMap("breakoutassets/background.png");
        resume = gameEngine.loadBitMap("breakoutassets/resume.png");
        gameOver = gameEngine.loadBitMap("breakoutassets/gameover.png");
        font = gameEngine.loadFont("breakoutassets/font.ttf");
        bounceSound = gameEngine.loadSound("breakoutassets/bounce.wav");
        blockSound = gameEngine.loadSound("breakoutassets/blocksplosion.wav");
        gameOverSound = gameEngine.loadSound("breakoutassets/gameover.wav");

    }

    @Override
    public void update(float deltaTime)
    {
        if (world.gameOver)
        {
            state = State.GameOver;
        }
        if (state == State.Paused && gameEngine.getTouchEvents().size() > 0)
        {
            state = State.Running;
            resume();
        }
        if (state == State.GameOver && gameEngine.getTouchEvents().size() > 0)
        {
            List<TouchEvent> events = gameEngine.getTouchEvents();
            int eventsSize = events.size();
            for (int i = 0; i < eventsSize; i++)
            {
                if (events.get(i).type == TouchEvent.TouchEventType.Up)
                {
                    gameEngine.setScreen(new MainMenuScreen(gameEngine));
                    return;
                }
            }
        }
        if (state == State.Running && gameEngine.getTouchY(0) < 35 && gameEngine.getTouchX(0) > 280)
        {
            state = State.Paused;
            pause();
            return;
        }

        //load level 2 resources
        gameEngine.drawBitmap(background, 0, 0);
        if (state == State.Running)
        {
            world.update(deltaTime, gameEngine.getAccelerometer()[0]);
        }
        Log.d("GameScreenL2","Render level 2");
        renderer.render();
        gameEngine.drawText(font, ("Lives: " + world.lives + "  Points: ") + world.points, 21, 24, Color.GREEN, 12);

        if (state == State.Paused)
        {
            gameEngine.drawBitmap(resume, (160 - resume.getWidth() / 2), 240 - resume.getHeight() / 2);
        }
        if (state == State.GameOver)
        {
            gameEngine.drawBitmap(gameOver, 160 - gameOver.getWidth() / 2, 240 - gameOver.getHeight() / 2);
        }
    }

    @Override
    public void pause()
    {
        if (state == State.Running)
        {
            state = State.Paused;
        }
        gameEngine.music.pause();
    }

    @Override
    public void resume()
    {
        gameEngine.music.play();
    }

    @Override
    public void dispose()
    {

    }
}

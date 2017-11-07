package dk.andreas.soeren.gameengine.Carscroller;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;

import java.util.List;

import dk.andreas.soeren.gameengine.GameEngine;
import dk.andreas.soeren.gameengine.Screen;
import dk.andreas.soeren.gameengine.Sound;
import dk.andreas.soeren.gameengine.TouchEvent;

/**
 * Created by Andreas on 03-Oct-17.
 */

public class GameScreen extends Screen
{
    enum State
    {
        Paused,
        Running,
        GameOver
    }
    World world;
    WorldRenderer renderer;
    State state = State.Running;


    Bitmap background = null;
    float backgroundX = 0;
    Bitmap resume = null;
    Bitmap gameOver = null;
    Typeface font = null;
    Sound bounceSound = null;
    Sound blockSound = null;
    public Sound gameOverSound = null;

    public GameScreen(GameEngine gameEngine)
    {
        super(gameEngine);
        world = new World(gameEngine, new CollisionListener()
        {
            public void collisionWall()
            {
                bounceSound.play(1);
            }

            @Override
            public void collisionMonster()
            {
                blockSound.play(1);
            }

            @Override
            public void gameover()
            {
                gameOverSound.play(1);
            }

        });
        renderer = new WorldRenderer(gameEngine, world);
        background = gameEngine.loadBitMap("carscrollerassets/xcarbackground.png");
        resume = gameEngine.loadBitMap("carscrollerassets/resume.png");
        gameOver = gameEngine.loadBitMap("carscrollerassets/gameover.png");
        font = gameEngine.loadFont("carscrollerassets/font.ttf");
        bounceSound = gameEngine.loadSound("carscrollerassets/bounce.wav");
        blockSound = gameEngine.loadSound("carscrollerassets/blocksplosion.wav");
        gameOverSound = gameEngine.loadSound("carscrollerassets/gameover.wav");

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

        if (state == State.Running)
        {
            //scroll the background
            backgroundX += 50 * deltaTime;
            if (backgroundX > 2700 - 480)
            {
                backgroundX = 0;
            }
            world.update(deltaTime, gameEngine.getAccelerometer()[0]);
        }
        //draw background
        gameEngine.drawBitmap(background, 0, 0, (int)backgroundX, 0, 480, 320);
        //draw all the game objects
        renderer.render();
        gameEngine.drawText(font, ("Lives: " + world.lives + "  Points: ") + world.points, 21, 24, Color.GREEN, 12);

        if (state == State.Paused)
        {
            gameEngine.drawBitmap(resume, (240 - resume.getWidth() / 2), 160 - resume.getHeight() / 2);
        }
        if (state == State.GameOver)
        {
            gameEngine.drawBitmap(gameOver, 240 - gameOver.getWidth() / 2, 160 - gameOver.getHeight() / 2);
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

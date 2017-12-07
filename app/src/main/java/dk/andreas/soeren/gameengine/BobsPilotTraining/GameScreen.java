package dk.andreas.soeren.gameengine.BobsPilotTraining;

import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;

import java.util.List;


import dk.andreas.soeren.gameengine.GameEngine;
import dk.andreas.soeren.gameengine.Screen;
import dk.andreas.soeren.gameengine.Sound;
import dk.andreas.soeren.gameengine.TouchEvent;

public class GameScreen extends Screen
{
    WorldRenderer renderer = null;
    World world = null;
    boolean touchReleased = false;
    String assetsMap = "bobspilottrainingassets/";
    Sound gameOverSound;


    public GameScreen(GameEngine gameEngine)
    {
        super(gameEngine);
        world = new World(gameEngine, new CollisionListener()
        {
            public void collisionWall()
            {
                gameOverSound.play(1);
            }
            @Override
            public void collisionPaddle()
            {
                gameOverSound.play(1);
            }
            @Override
            public void collisionBlock()
            {
                gameOverSound.play(1);
            }
            @Override
            public void gameover()
            {
                gameOverSound.play(1);
            }
        });
        renderer = new WorldRenderer(gameEngine, world);
        gameOverSound = gameEngine.loadSound(assetsMap + "splash.wav");
    }

    @Override
    public void update(float deltaTime)
    {
        if (world.gameOver)
        {

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
                    gameEngine.getTouchEvents().clear();
                    gameEngine.setScreen(new MainMenuScreen(gameEngine, false));
                    return;
                }
            }


        }
        world.update(deltaTime);
        renderer.render(deltaTime);
    }

    @Override
    public void pause()
    {
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

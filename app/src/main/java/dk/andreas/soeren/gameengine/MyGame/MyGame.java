package dk.andreas.soeren.gameengine.MyGame;

import dk.andreas.soeren.gameengine.GameEngine;
import dk.andreas.soeren.gameengine.Screen;

public class MyGame extends GameEngine
{

    @Override
    public Screen createStartScreen()
    {
        return new GameScreen(this);
    }
}

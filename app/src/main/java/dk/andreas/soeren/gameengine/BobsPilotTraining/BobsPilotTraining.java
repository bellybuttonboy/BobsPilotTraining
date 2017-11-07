package dk.andreas.soeren.gameengine.BobsPilotTraining;

import dk.andreas.soeren.gameengine.GameEngine;
import dk.andreas.soeren.gameengine.Screen;

/**
 * Created by Andreas on 07-Nov-17.
 */

public class BobsPilotTraining extends GameEngine
{
    @Override
    public Screen createStartScreen()
    {
        return new MainMenuScreen(this);
    }
}

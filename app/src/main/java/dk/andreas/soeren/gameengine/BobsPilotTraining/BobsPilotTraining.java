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
        music = loadMusic("bobspilottrainingassets/theme.wav");
        music.setLooping(true);
        return new MainMenuScreen(this, true);

    }

    public void onPause()
    {
        super.onPause();
        music.pause();
    }

    public void onResume()
    {
        super.onResume();
        music.play();
    }
}

package dk.andreas.soeren.gameengine.Carscroller;
import dk.andreas.soeren.gameengine.GameEngine;
import dk.andreas.soeren.gameengine.Screen;

/**
 * Created by Andreas on 31-Oct-17.
 */

public class Carscroller extends GameEngine
{
    @Override
    public Screen createStartScreen()
    {
        music = loadMusic("breakoutassets/music.ogg");
        return new MainMenuScreen(this);
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

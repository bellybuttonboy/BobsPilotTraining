package dk.andreas.soeren.gameengine;



/**
 * Created by Søren on 05-09-2017.
 */

public class TestGame extends GameEngine
{

    @Override
    public Screen createStartScreen()
    {
        return new TestScreen(this);
    }
}

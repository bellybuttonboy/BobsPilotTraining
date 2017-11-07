package dk.andreas.soeren.gameengine.MyGame;

import android.graphics.Bitmap;
import android.util.Log;
import dk.andreas.soeren.gameengine.GameEngine;

/**
 * Created by Andreas on 13-Oct-17.
 */

public class WorldRenderer
{
    GameEngine gameEngine;
    World world;
    Bitmap froggyImage;
    Bitmap background;
    Bitmap lilyPadOneImage;
    Bitmap lilyPadTwoImage;
    Bitmap lilyPadThreeImage;

    public WorldRenderer(GameEngine gameEngine, World world)
    {
        this.gameEngine = gameEngine;
        this.world = world;
        froggyImage = this.gameEngine.loadBitMap("mygameassets/frog.png");
        background = this.gameEngine.loadBitMap("mygameassets/background.png");
        lilyPadOneImage = this.gameEngine.loadBitMap("mygameassets/lilypad.png");
        lilyPadTwoImage = this.gameEngine.loadBitMap("mygameassets/lilypad.png");
        lilyPadThreeImage = this.gameEngine.loadBitMap("mygameassets/lilypad.png");
    }

    public void render()
    {
        Log.wtf("tag", "" + world.froggy.x + world.froggy.y);
        gameEngine.drawBitmap(background, 0, 0);
        gameEngine.drawBitmap(lilyPadOneImage, world.padOne.x, world.padOne.y);
        gameEngine.drawBitmap(lilyPadTwoImage, world.padTwo.x, world.padTwo.y);
        gameEngine.drawBitmap(lilyPadThreeImage, world.padThree.x, world.padThree.y);
        gameEngine.drawBitmap(froggyImage, world.froggy.x, world.froggy.y);

        //gameEngine.drawBitmap(lilyPadTwoImage);
        //gameEngine.drawBitmap(lilyPadThreeImage);
    }
}

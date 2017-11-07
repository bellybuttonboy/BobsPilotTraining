package dk.andreas.soeren.gameengine.Carscroller;

import android.graphics.Bitmap;

import dk.andreas.soeren.gameengine.GameEngine;

/**
 * Created by Andreas on 10-Oct-17.
 */

public class WorldRenderer
{
    GameEngine gameEngine;
    World world;
    Bitmap carImage;
    Bitmap monsterImage;


    public WorldRenderer(GameEngine gameEngine, World world)
    {
        this.gameEngine = gameEngine;
        this.world = world;
        carImage = this.gameEngine.loadBitMap("carscrollerassets/xbluecar2.png");
        monsterImage = this.gameEngine.loadBitMap("carscrollerassets/xyellowmonster.png");


    }

    public void render()
    {
        gameEngine.drawBitmap(carImage, world.car.x, world.car.y);
        for (int i = 0; i < world.maxMonsters; i++)
        {
            gameEngine.drawBitmap(monsterImage, world.monsterList.get(i).x, world.monsterList.get(i).y);
        }

    }
}

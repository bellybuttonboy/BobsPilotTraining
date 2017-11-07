package dk.andreas.soeren.gameengine.Breakout;

import android.graphics.Bitmap;

import dk.andreas.soeren.gameengine.GameEngine;

/**
 * Created by Andreas on 10-Oct-17.
 */

public class WorldRenderer2
{
    GameEngine gameEngine;
    World world;
    Bitmap ballImage;
    Bitmap paddleImage;
    Bitmap blocksImage;

    public WorldRenderer2(GameEngine gameEngine, World world)
    {
        this.gameEngine = gameEngine;
        this.world = world;
        ballImage = this.gameEngine.loadBitMap("breakoutassets/ball.png");
        paddleImage = this.gameEngine.loadBitMap("breakoutassets/paddle.png");
        blocksImage = this.gameEngine.loadBitMap("breakoutassets/blocks.png");

    }

    public void render()
    {
        gameEngine.drawBitmap(ballImage, world.ball.x, world.ball.y);
        gameEngine.drawBitmap(paddleImage, world.paddle.x, world.paddle.y);

        int blocksArraySize = world.blocks.size();
        Block block = null;
        for (int i = 0; i < blocksArraySize; i++)
        {
            block = world.blocks.get(i);
            gameEngine.drawBitmap(blocksImage, (int)block.x, (int)block.y, 0, (int)(block.type * block.HEIGHT), (int)block.WIDITH, (int)block.HEIGHT);
        }
    }
}

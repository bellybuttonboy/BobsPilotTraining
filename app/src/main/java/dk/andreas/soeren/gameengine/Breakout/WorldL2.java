package dk.andreas.soeren.gameengine.Breakout;

import android.util.Log;

import java.util.ArrayList;

import dk.andreas.soeren.gameengine.GameEngine;
import dk.andreas.soeren.gameengine.MyGame.*;

/**
 * Created by Andreas on 24-Oct-17.
 */

public class WorldL2 extends World
{
    public static final float MIN_X = 0;
    public static final float MAX_X = 319;
    public static final float MIN_Y = 35;
    public static final float MAX_Y = 479;
    Ball ball = new Ball();
    Paddle paddle = new Paddle();
    ArrayList<Block> blocks = new ArrayList();
    GameEngine gameEngine;
    CollisionListener collisionListener;
    boolean gameOver = false;
    int points = 0;
    int lives = 3;
    int paddleHits = 0;
    int advance = 10;

    public WorldL2(GameEngine gameEngine, CollisionListener collisionListener)
    {
        super(gameEngine, collisionListener);
        this.gameEngine = gameEngine;
        this.collisionListener = collisionListener;
        generateBlocks();
        Log.d("WorldLevel2","level 2 created");
    }

    private void generateBlocks()
    {
        blocks.clear();
        for (int y = 50, type = 0; y < 50 + 8 * Block.HEIGHT; y = y + (int)(Block.HEIGHT + 2), type ++)
        {
            for (int x = 20; x < MAX_X - Block.WIDITH/2; x = x + (int)(Block.WIDITH)) // FOR EACH COLUMN
            {
                blocks.add(new Block(x, y, type));
            }
        }
    }


    public void update(float deltatime, float accelX)
    {
        ball.x = (int) (ball.x + ball.vx * deltatime);
        ball.y = (int) (ball.y + ball.vy * deltatime);

        if (ball.x < MIN_X)
        {
            ball.vx = -ball.vx;
            ball.x = (int)MIN_X;
            collisionListener.collisionWall();
        }

        if (ball.x > MAX_X - ball.WIDTH)
        {
            ball.vx = -ball.vx;
            ball.x = (int)(MAX_X - ball.WIDTH);
            collisionListener.collisionWall();
        }

        if (ball.y < MIN_Y)
        {
            ball.vy = -ball.vy;
            ball.y = (int)MIN_Y;
            collisionListener.collisionWall();
        }
        // The ball goes below the bottom edge
        if (ball.y + Ball.HEIGHT > MAX_Y)
        {
            lives = lives -1;
            if (lives == 0)
            {
                gameOver = true;
                collisionListener.gameover();
                return;
            }
            else
            {
                collisionListener.collisionBlock();
                ball.x = 145;
                ball.y = 255;
                ball.vx = 150;
                ball.vy = -150;
            }
        }
        //PADLE LOGIC
        paddle.x = (int) (paddle.x + accelX * deltatime * 50);

        if (paddle.x < MIN_X)
        {
            paddle.x = (int)MIN_X;
        }

        if (paddle.x > MAX_X - paddle.Width)
        {
            paddle.x = (int) (MAX_X - paddle.Width);
        }

        if (gameEngine.isTouchDown(0)) // For testing
        {
            if(gameEngine.getTouchY(0) > 450)
            {
                paddle.x = gameEngine.getTouchX(0);
            }
        }

        collideBallPaddle();
        collideBallBlocks(deltatime);

        //If all blocks are removed, regenerate or better: start a new level :-)
        if (blocks.size() == 0)
        {
            generateBlocks();
            ball.y = 255;
            ball.x = 145;
        }

    }

    private void collideBallBlocks(float deltatime)
    {
        Block block = null;
        for(int i = 0; i < blocks.size(); i++)
        {
            block = blocks.get(i);
            if (collideRectangles(block.x, block.y, block.WIDITH, block.HEIGHT, ball.x, ball.y, ball.WIDTH, ball.HEIGHT))
            {
                blocks.remove(i);
                collisionListener.collisionBlock();
                i = i-1;
                float oldVX = ball.vx;
                float oldVY = ball.vy;
                reflectBall(ball, block);
                ball.x = (int) (ball.x - oldVX * deltatime * 1.01f);
                ball.y = (int) (ball.y - oldVY * deltatime * 1.01f);
                points += 10 - block.type;
            }
        }
    }

    public void reflectBall(Ball ball, Block block)
    {
        // check the top left corner of the block
        if (collideRectangles(ball.x, ball.y, Ball.WIDTH, Ball.HEIGHT, block.x, block.y, 1, 1))
        {
            if (ball.vx > 0)
            {
                ball.vx = -ball.vx;
            }
            if (ball.vx > 0)
            {
                ball.vx = -ball.vx;
            }
        }
        //check top right cornor
        if (collideRectangles(ball.x, ball.y, Ball.WIDTH, Ball.HEIGHT, block.x + Block.WIDITH, block.y, 1, 1))
        {
            if (ball.vx < 0) ball.vx = -ball.vx;
            if (ball.vy > 0) ball.vy = -ball.vy;
            return;
        }
        //check the buttom left cornor of the block
        if(collideRectangles(ball.x, ball.y, Ball.WIDTH, Ball.HEIGHT, block.x, block.y + Block.HEIGHT, 1,1))
        {
            if (ball.vx > 0)
            {
                ball.vx = -ball.vx;
            }
            if (ball.vy < 0)
            {
                ball.vy = -ball.vy;
            }
            return;
        }
        //check the bottom right corner of the block
        if (collideRectangles(ball.x, ball.y, Ball.WIDTH, Ball.HEIGHT, block.x + Block.WIDITH, block.y + Block.HEIGHT, 1, 1))
        {
            if (ball.vx < 0)
            {
                ball.vx = -ball.vx;
            }
            if (ball.vy < 0)
            {
                ball.vy = -ball.vy;
            }
        }
        // check the top edge of the block
        if (collideRectangles(ball.x, ball.y, Ball.WIDTH, Ball.HEIGHT, block.x, block.y, Block.WIDITH, 1))
        {
            if (ball.vy > 0)
            {
                ball.vy = -ball.vy; // the other case should be impossible
            }
            return;
        }
        //check the bottom edge of the block
        if (collideRectangles(ball.x, ball.y, Ball.WIDTH, Ball.HEIGHT, block.x, block.y + Block.HEIGHT, Block.WIDITH, 1))
        {
            ball.vy = -ball.vy;
            return;
        }
        //check the left edge of the block
        if(collideRectangles(ball.x, ball.y, Ball.WIDTH, Ball.HEIGHT, block.x, block.y, 1, Block.HEIGHT))
        {
            ball.vx = -ball.vx;
            return;
        }
        //check the right edge of the block
        if (collideRectangles(ball.x, ball.y, Ball.WIDTH, Ball.HEIGHT, block.x + Block.WIDITH, block.y, 1, Block.HEIGHT))
        {
            ball.vx = -ball.vx;
            return;
        }


    }

    private boolean collideRectangles(float x1, float y1, float width1, float height1,
                                      float x2, float y2, float width2, float height2)
    {
        if (x1 < x2 + width2 &&
                x1 + width1 > x2 &&
                y1 + height1 > y2 &&
                y1 < y2 + height2)
        {
            return true;
        }
        return false;
    }


    private void collideBallPaddle()
    {
        if (ball.y + ball.HEIGHT >= paddle.y &&
                ball.x < paddle.x + Paddle.Width &&
                ball.x + Ball.WIDTH > paddle.x)
        {
            ball.y = paddle.y - (int)ball.HEIGHT - 2;
            ball.vy = -ball.vy;
            collisionListener.collisionPaddle();
            paddleHits++;
            if (paddleHits == 2) // to be adjusted for normal play
            {
                paddleHits = 0;
                advance += 10;
                advanceBlocks();
            }
        }
    }

    private void advanceBlocks()
    {
        Block block;
        int size = blocks.size();
        for (int i = 0; i < size; i++)
        {
            block = blocks.get(i);
            block.y += advance;
        }
    }
}

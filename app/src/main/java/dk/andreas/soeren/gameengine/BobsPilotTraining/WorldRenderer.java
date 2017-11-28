package dk.andreas.soeren.gameengine.BobsPilotTraining;

import android.graphics.Bitmap;

import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;

import dk.andreas.soeren.gameengine.GameEngine;

public class WorldRenderer
{
    GameEngine gameEngine;
    World world;
    Bitmap[] colorArray = new Bitmap[5];
    Bitmap backgroundPink = null;
    Bitmap backgroundYellow = null;
    Bitmap backgroundGreen = null;
    Bitmap backgroundPurple = null;
    Bitmap backgroundOrange = null;
    Bitmap planeRightImg = null;
    Bitmap planeLeftImg = null;
    Bitmap planeUpImg = null;
    Bitmap planeDownImg = null;
    Bitmap planeToDraw = null;
    Bitmap enemySquare = null;
    Bitmap enemyTriangle = null;
    Bitmap enemyCircle = null;
    String assetsMap = "bobspilottrainingassets/";
    Plane plane = null;
    Typeface font = null;
    int lastTime = 0;
    int colorTracker = 0;

    public WorldRenderer(GameEngine gameEngine, World world)
    {
        this.gameEngine = gameEngine;
        this.world = world;
        backgroundPink = gameEngine.loadBitMap(assetsMap + "Pink.png");
        backgroundYellow = gameEngine.loadBitMap(assetsMap + "Yellow.png");
        backgroundOrange = gameEngine.loadBitMap(assetsMap + "Orange.png");
        backgroundGreen = gameEngine.loadBitMap(assetsMap + "Green.png");
        backgroundPurple = gameEngine.loadBitMap(assetsMap + "Purple.png");
        planeRightImg = gameEngine.loadBitMap(assetsMap + "planeRight.png");
        planeLeftImg = gameEngine.loadBitMap(assetsMap + "planeLeft.png");
        planeUpImg = gameEngine.loadBitMap(assetsMap + "planeUp.png");
        planeDownImg = gameEngine.loadBitMap(assetsMap + "planeDown.png");
        enemySquare = gameEngine.loadBitMap(assetsMap + "EnemySquare.png");
        enemyTriangle = gameEngine.loadBitMap(assetsMap + "EnemyTriangle.png");
        enemyCircle = gameEngine.loadBitMap(assetsMap + "EnemyCircle.png");
        font = gameEngine.loadFont(assetsMap + "Chewy-Regular.ttf");
        planeToDraw = planeRightImg;
        plane = world.plane;
        createColorArray();
    }

    public void render(float deltatime)
    {
        determineBackgroundToDraw(world.passedTime);
        gameEngine.drawBitmap(colorArray[colorTracker], 0, 0);

        determinePlane();
        gameEngine.drawBitmap(planeToDraw, plane.x, plane.y);
        gameEngine.drawBitmap(enemySquare, world.SquareEnemy.x, world.SquareEnemy.y);
        gameEngine.drawBitmap(enemyTriangle, world.TriangleEnemy.x, world.TriangleEnemy.y);

        if(world.CircleEnemy != null)
        {
            gameEngine.drawBitmap(enemyCircle, world.CircleEnemy.x, world.CircleEnemy.y);
        }


        if (world.gameOver)
        {
            gameEngine.drawText(font,"GAMEOVER!!!", 90, 100, Color.RED, 28);
            gameEngine.drawText(font,"Your time: " + (int)world.passedTime, 97, 135, Color.RED, 28);

            if (!world.recordsUpdated) // Check to see if we have updated the record this playthrough
            {
                world.updateRecords((int) world.passedTime);
                world.loadRecords(gameEngine);
            }

            gameEngine.drawText(font,"Records:", 118, 250, Color.RED, 28);
            gameEngine.drawText(font,"1. : " + world.records[0], 135, 290, Color.RED, 28);
            gameEngine.drawText(font, "2. : " + world.records[1], 135, 330, Color.RED, 28);
            gameEngine.drawText(font, "3. : " + world.records[2], 135, 370, Color.RED, 28);


        }

    }

    private void createColorArray()
    {
        colorArray[0] = backgroundPurple;
        colorArray[1] = backgroundOrange;
        colorArray[2] = backgroundYellow;
        colorArray[3] = backgroundGreen;
        colorArray[4] = backgroundPink;
    }

    private void determineBackgroundToDraw(float passedTime)
    {

        if(passedTime - lastTime > 10)
        {
            lastTime = (int) passedTime;
            colorTracker++;
            if(colorTracker > 4)
            {
                colorTracker = 0;
            }
        }
    }

    private void determinePlane()
    {
        int differenceX = plane.x - plane.previousX;
        int differenceY = plane.y - plane.previousY;

        if (differenceX > 2 || differenceX < -2 || differenceY > 2 || differenceY < -2)
        {
            if (differenceX > 0 && differenceX > differenceY && differenceX > -differenceY)
            {
                planeToDraw = planeRightImg;
            }
            else if (differenceX < 0 && -differenceX > differenceY && -differenceX > -differenceY)
            {
                planeToDraw = planeLeftImg;
            }
            if (differenceY > 0 && differenceY > differenceX && differenceY > -differenceX)
            {
                planeToDraw = planeDownImg;
            }
            if (differenceY < 0 && -differenceY > differenceX && -differenceY > -differenceX)
            {
                planeToDraw = planeUpImg;
            }
        }
    }
}

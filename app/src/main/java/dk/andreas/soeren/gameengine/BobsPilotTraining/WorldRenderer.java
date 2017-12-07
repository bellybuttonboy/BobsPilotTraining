package dk.andreas.soeren.gameengine.BobsPilotTraining;

import android.graphics.Bitmap;

import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;

import dk.andreas.soeren.gameengine.GameEngine;
import dk.andreas.soeren.gameengine.Sound;

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
    Sound recordSound = null;
    String assetsMap = "bobspilottrainingassets/";
    Plane plane = null;
    Typeface font = null;
    int lastTime = 0;
    int colorTracker = 0;
    boolean newRecord;

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
        recordSound = gameEngine.loadSound(assetsMap + "tada.wav");
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
            gameEngine.drawText(font,"GAMEOVER!!!", 63, 150, Color.RED, 40);

            if (world.passedTime < 10) // Check on how far to the left we have to render it depending on length of your time
            {
                gameEngine.drawText(font,"Your time: " + (int)world.passedTime, 70, 200, Color.RED, 40);
            }
            else if (world.passedTime > 10 && world.passedTime < 100)
            {
                gameEngine.drawText(font,"Your time: " + (int)world.passedTime, 62, 200, Color.RED, 40);
            }
            else if (world.passedTime > 100)
            {
                gameEngine.drawText(font,"Your time: " + (int)world.passedTime, 55, 200, Color.RED, 40);
            }

            if (!world.recordsUpdated) // Check to see if we have updated the record this playthrough
            {
                if(world.updateRecords((int) world.passedTime))
                {
                    newRecord = true;
                    recordSound.play(1);
                }
                world.loadRecords(gameEngine);
            }
            if (newRecord)
            {
                gameEngine.drawText(font,"NEW RECORD!", 55, 300, Color.RED, 40);
            }
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

package dk.andreas.soeren.gameengine.BobsPilotTraining;

import android.graphics.Bitmap;

import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

import dk.andreas.soeren.gameengine.GameEngine;

public class WorldRenderer
{
    GameEngine gameEngine;
    World world;
    Bitmap background = null;
    Bitmap planeRightImg = null;
    Bitmap planeLeftImg = null;
    Bitmap planeUpImg = null;
    Bitmap planeDownImg = null;
    Bitmap planeToDraw = null;
    Bitmap enemySquare = null;
    Bitmap enemyTriangle = null;
    String assetsMap = "bobspilottrainingassets/";
    Plane plane = null;
    Typeface font = null;
    float passedTime = 0;

    public WorldRenderer(GameEngine gameEngine, World world)
    {
        this.gameEngine = gameEngine;
        this.world = world;
        background = gameEngine.loadBitMap(assetsMap + "GameBackground.png");
        planeRightImg = gameEngine.loadBitMap(assetsMap + "planeRight.png");
        planeLeftImg = gameEngine.loadBitMap(assetsMap + "planeLeft.png");
        planeUpImg = gameEngine.loadBitMap(assetsMap + "planeUp.png");
        planeDownImg = gameEngine.loadBitMap(assetsMap + "planeDown.png");
        enemySquare = gameEngine.loadBitMap(assetsMap + "EnemySquare.png");
        enemyTriangle = gameEngine.loadBitMap(assetsMap + "EnemyTriangle.png");
        font = gameEngine.loadFont(assetsMap + "font.ttf");
        planeToDraw = planeRightImg;
        plane = world.plane;
    }

    public void render(float deltatime)
    {
        gameEngine.drawBitmap(background, 0, 0);
        determinePlane();
        gameEngine.drawBitmap(planeToDraw, plane.x, plane.y);
        gameEngine.drawBitmap(enemySquare, world.SquareEnemy.x, world.SquareEnemy.y);
        gameEngine.drawBitmap(enemyTriangle, world.TriangleEnemy.x, world.TriangleEnemy.y);
        if (!world.gameOver)
        {
            passedTime = passedTime + deltatime;
        }
        if (world.gameOver)
        {
            gameEngine.drawText(font,"GAMEOVER!!!", 115, 150, Color.RED, 12);
            gameEngine.drawText(font,"Your time: " + (int)passedTime, 115, 165, Color.RED, 12);
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

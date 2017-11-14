package dk.andreas.soeren.gameengine.BobsPilotTraining;

import android.graphics.Bitmap;

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
    String assetsMap = "bobspilottrainingassets/";
    Plane plane = null;


    public WorldRenderer(GameEngine gameEngine, World world)
    {
        this.gameEngine = gameEngine;
        this.world = world;
        background = gameEngine.loadBitMap(assetsMap + "GameBackground.png");
        planeRightImg = gameEngine.loadBitMap(assetsMap + "planeRight.png");
        planeLeftImg = gameEngine.loadBitMap(assetsMap + "planeLeft.png");
        planeUpImg = gameEngine.loadBitMap(assetsMap + "planeUp.png");
        planeDownImg = gameEngine.loadBitMap(assetsMap + "planeDown.png");
        planeToDraw = planeRightImg;
        plane = world.plane;
    }

    public void render()
    {
        gameEngine.drawBitmap(background, 0, 0);
        determinePlane();
        gameEngine.drawBitmap(planeToDraw, plane.x, plane.y);

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

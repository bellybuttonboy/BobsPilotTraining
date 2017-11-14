package dk.andreas.soeren.gameengine.BobsPilotTraining;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dk.andreas.soeren.gameengine.GameEngine;


public class World
{
    public static final float MIN_X = 0;
    public static final float MAX_X = 319;
    public static final float MIN_Y = 0;
    public static final float MAX_Y = 479;
    GameEngine gameEngine;
    //CollisionListener collisionListener;
    boolean gameOver = false;
    public Plane plane = null;
    List<Enemy> enemyList = new ArrayList<>();
    Enemy SquareEnemy;
    Enemy TriangleEnemy;

    public World(GameEngine gameEngine)
    {
        this.gameEngine = gameEngine;
        plane = new Plane();
        setUpEnemies();
    }


    public void update(float deltatime)
    {
        SquareEnemy.x = (int) (SquareEnemy.x + SquareEnemy.vx * deltatime);
        SquareEnemy.y = (int) (SquareEnemy.y + SquareEnemy.vy * deltatime);

        TriangleEnemy.x = (int) (TriangleEnemy.x + TriangleEnemy.vx * deltatime);
        TriangleEnemy.y = (int) (TriangleEnemy.y + TriangleEnemy.vy * deltatime);


        updatePlane();
        checkBoundariesEnemy(SquareEnemy);
        checkBoundariesEnemy(TriangleEnemy);

        checkForCollisions();


    }

    public void checkForCollisions()
    {
        int enemySize = enemyList.size();
        Enemy enemy = null;
        for (int i = 0; i < enemySize; i++)
        {
            enemy = enemyList.get(i);

            if (collideRectangles(enemy.x, enemy.y, enemy.WIDTH, enemy.HEIGHT, plane.x, plane.y, plane.WIDTH, plane.HEIGHT))
            {
                gameOver = true;
            }
        }
    }

    public void setUpEnemies()
    {
        SquareEnemy = new Enemy(40, 40, 319 - 40, 0);
        TriangleEnemy = new Enemy(29, 24, 0, 479 - 24);

        enemyList.add(SquareEnemy);
        enemyList.add(TriangleEnemy);
    }

    public void updatePlane()
    {
        if (gameEngine.isTouchDown(0)) // move plane
        {
            if (gameEngine.getTouchX(0) - plane.x > -25 && gameEngine.getTouchX(0) - plane.x < 0 ||
                    gameEngine.getTouchX(0) - plane.x < 60 && gameEngine.getTouchX(0) - plane.x > 0) // husk at ændre når breden ændres
            {
                if (gameEngine.getTouchY(0) - plane.y > -25 && gameEngine.getTouchY(0) - plane.y < 0 ||
                        gameEngine.getTouchY(0) - plane.y < 25 && gameEngine.getTouchY(0) - plane.y > 0 )
                {
                    plane.previousX = plane.x;
                    plane.previousY = plane.y;

                    plane.x = gameEngine.getTouchX(0);
                    plane.y = gameEngine.getTouchY(0);
                }
            }
        }
    }

    public void checkBoundariesEnemy(Enemy enemy)
    {
        if (enemy.x < MIN_X)
        {
            enemy.x = (int) MIN_X;
            enemy.vx = -enemy.vx;
        }
        if (enemy.x + enemy.WIDTH > MAX_X)
        {
            enemy.x = (int) (MAX_X - enemy.WIDTH);
            enemy.vx = -enemy.vx;
        }

        if (enemy.y < MIN_Y)
        {
            enemy.y = (int) MIN_Y;
            enemy.vy = -enemy.vy;
        }
        if (enemy.y + enemy.HEIGHT > MAX_Y)
        {
            enemy.y = (int) (MAX_Y - enemy.HEIGHT);
            enemy.vy = -enemy.vy;
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
}


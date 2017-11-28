package dk.andreas.soeren.gameengine.BobsPilotTraining;

import android.content.Context;
import android.content.SharedPreferences;
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
    Enemy CircleEnemy;
    String[] records = new String[3];
    SharedPreferences sharedPreferences;
    boolean recordsUpdated = false;
    float passedTime = 0;


    public World(GameEngine gameEngine)
    {
        this.gameEngine = gameEngine;
        plane = new Plane();
        setUpEnemies();
        loadRecords(gameEngine);
    }


    public void update(float deltatime)
    {

        if(!gameOver)
        {
            passedTime = passedTime + deltatime;
        }
        SquareEnemy.x = (int) (SquareEnemy.x + SquareEnemy.vx * deltatime);
        SquareEnemy.y = (int) (SquareEnemy.y + SquareEnemy.vy * deltatime);

        TriangleEnemy.x = (int) (TriangleEnemy.x + TriangleEnemy.vx * deltatime);
        TriangleEnemy.y = (int) (TriangleEnemy.y + TriangleEnemy.vy * deltatime);

        if (CircleEnemy != null)
        {
            CircleEnemy.x = (int) (CircleEnemy.x + CircleEnemy.vx * deltatime);
            CircleEnemy.y = (int) (CircleEnemy.y + CircleEnemy.vy * deltatime);
        }

        if(passedTime > 30)
        {
            if(CircleEnemy == null)
            {
                CircleEnemy = new Enemy(40,40,(int)MIN_X, (int)MIN_Y);
                enemyList.add(CircleEnemy);
            }
        }

        updatePlane();
        int enemyListSize = enemyList.size();
        for (int i = 0; i < enemyListSize; i++)
        {
            checkBoundariesEnemy(enemyList.get(i));
        }

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
            if (gameEngine.getTouchX(0) - plane.x > -35 && gameEngine.getTouchX(0) - plane.x < 0 ||
                    gameEngine.getTouchX(0) - plane.x < 80 && gameEngine.getTouchX(0) - plane.x > 0) // husk at ændre når breden ændres
            {
                if (gameEngine.getTouchY(0) - plane.y > -35 && gameEngine.getTouchY(0) - plane.y < 0 ||
                        gameEngine.getTouchY(0) - plane.y < 80 && gameEngine.getTouchY(0) - plane.y > 0 )
                {
                    plane.previousX = plane.x;
                    plane.previousY = plane.y;

                    plane.x = gameEngine.getTouchX(0);
                    plane.y = gameEngine.getTouchY(0);
                }
            }
        }

        //check if it's out of the scree
        if (plane.x + plane.WIDTH > MAX_X)
        {
            plane.x = (int) MAX_X - plane.WIDTH;
        }

        if (plane.x < MIN_X)
        {
            plane.x = (int) MIN_X;
        }

        if(plane.y + plane.HEIGHT > MAX_Y)
        {
            plane.y = (int) MAX_Y - plane.HEIGHT;
        }

        if(plane.y < MIN_Y)
        {
            plane.y = (int) MIN_Y;
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

    public void loadRecords(Context context)
    {
        sharedPreferences = context.getSharedPreferences("DATA", Context.MODE_PRIVATE);
        records[0] = sharedPreferences.getString("first", "0");
        records[1] = sharedPreferences.getString("second", "0");
        records[2] = sharedPreferences.getString("third","0");

    }

    public boolean updateRecords(int time)
    {
        boolean updated = false;
        if (time >= Integer.parseInt(records[0]))
        {
            sharedPreferences.edit().putString("first","" + time).apply();
            sharedPreferences.edit().putString("second", records[0]).apply();
            sharedPreferences.edit().putString("third", records[1]).apply();
            updated = true;
        }
        else if (time >= Integer.parseInt(records[1]))
        {
            sharedPreferences.edit().putString("second", "" + time).apply();
            sharedPreferences.edit().putString("third", records[1]).apply();
            updated = true;
        }
        else if (time >= Integer.parseInt(records[2]))
        {
            sharedPreferences.edit().putString("third","" + time).apply();
            updated = true;
        }

        recordsUpdated = true;
        return updated;
    }
}


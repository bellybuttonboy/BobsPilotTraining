package dk.andreas.soeren.gameengine.BobsPilotTraining;


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
    CollisionListener collisionListener;
    boolean gameOver = false;
    public Plane plane = null;
    List<Enemy> enemyList = new ArrayList<>();
    Enemy SquareEnemy;
    Enemy TriangleEnemy;
    Enemy CircleEnemy;
    int speedUpCheck = 0;
    float passedTime = 0;


    public World(GameEngine gameEngine, CollisionListener collisionListener)
    {
        this.gameEngine = gameEngine;
        this.collisionListener = collisionListener;
        plane = new Plane();
        setUpEnemies();
    }


    public void update(float deltatime)
    {

        if(!gameOver)
        {
            passedTime = passedTime + deltatime;
        }

        speedIncrease();

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

    public void speedIncrease()
    {
        if(passedTime - speedUpCheck > 10)
        {
            SquareEnemy.vx = (int) (SquareEnemy.vx * 1.05);
            SquareEnemy.vy = (int) (SquareEnemy.vy * 1.05);

            TriangleEnemy.vx = (int) (TriangleEnemy.vx * 1.05);
            TriangleEnemy.vy = (int) (TriangleEnemy.vy * 1.05);

            if(CircleEnemy != null)
            {
                CircleEnemy.vx = (int) (CircleEnemy.vx * 1.05);
                CircleEnemy.vy = (int) (CircleEnemy.vy * 1.05);
            }

            speedUpCheck = (int) passedTime;
        }
    }

    public void checkForCollisions()
    {
        int enemySize = enemyList.size();
        Enemy enemy = null;
        for (int i = 0; i < enemySize; i++)
        {
            enemy = enemyList.get(i);

            if (collideRectangles(enemy.x, enemy.y, enemy.WIDTH, enemy.HEIGHT, plane.x, plane.y,
                    plane.WIDTH, plane.HEIGHT) && gameOver != true)
            {
                gameOver = true;
                collisionListener.gameover();
                return;
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

                    plane.x = gameEngine.getTouchX(0) - plane.WIDTH/2;
                    plane.y = gameEngine.getTouchY(0) - plane.HEIGHT/2;
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
            collisionListener.collisionWall();
        }
        if (enemy.x + enemy.WIDTH > MAX_X)
        {
            enemy.x = (int) (MAX_X - enemy.WIDTH);
            enemy.vx = -enemy.vx;
            collisionListener.collisionWall();
        }

        if (enemy.y < MIN_Y)
        {
            enemy.y = (int) MIN_Y;
            enemy.vy = -enemy.vy;
            collisionListener.collisionWall();
        }
        if (enemy.y + enemy.HEIGHT > MAX_Y)
        {
            enemy.y = (int) (MAX_Y - enemy.HEIGHT);
            enemy.vy = -enemy.vy;
            collisionListener.collisionWall();
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


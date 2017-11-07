package dk.andreas.soeren.gameengine.Carscroller;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dk.andreas.soeren.gameengine.GameEngine;

public class World
{
    public static final float MIN_X = 0;
    public static final float MAX_X = 479;
    public static final float MIN_Y = 28;
    public static final float MAX_Y = 319 - 28;
    Car car = new Car();
    List<Monster> monsterList = new ArrayList<>();
    public int maxMonsters = 3;
    GameEngine gameEngine;
    CollisionListener collisionListener;
    boolean gameOver = false;
    int points = 0;
    int lives = 3;

    public World(GameEngine gameEngine, CollisionListener collisionListener)
    {
        this.gameEngine = gameEngine;
        this.collisionListener = collisionListener;
        initializeMonster();
    }


    public void update(float deltatime, float accelY) // X
    {
        car.y = (int) (car.y + -accelY * deltatime * 50);



        if (gameEngine.isTouchDown(0)) // For testing
        {
            if(gameEngine.getTouchX(0) < 100)
            {
                car.y = gameEngine.getTouchY(0) - car.HEIGHT;
            }
        }

        // check top
        if (car.y < MIN_Y)
        {
            car.y = (int) MIN_Y;
        }
        // check bottom
        if(car.y > MAX_Y - car.HEIGHT)
        {
            car.y = (int) MAX_Y - car.HEIGHT;
        }

        //move the monsters
        Monster monster = null;
        for (int i = 0; i < maxMonsters; i++)
        {
            monster = monsterList.get(i);
            monster.x -= 100 * deltatime;
            if (monster.x < 0 - Monster.WIDTH) // monster left the screen to the left
            {
                Random random = new Random();
                monster.x = 500 + random.nextInt(100);
                monster.y = 28 + random.nextInt(235);

            }
        }
        collideCarMonster();
    }

    private void collideCarMonster()
    {
        Monster monster = null;
        for(int i = 0; i < maxMonsters; i++)
        {
            monster = monsterList.get(i);
            if (collideRectangles(car.x, car.y, car.WIDTH, car.HEIGHT, monster.x, monster.y, monster.WIDTH, monster.HEIGHT))
            {
                gameOver = true;
            }
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

    private void initializeMonster()
    {
        Random rand = new Random();

        for (int i = 0; i < maxMonsters; i++)
        {
            int randX = rand.nextInt(50);
            int randY = rand.nextInt(235);
            Monster monster = new Monster((500 + randX)+i*100, 28 + randY);
            monsterList.add(monster);
        }
    }
}

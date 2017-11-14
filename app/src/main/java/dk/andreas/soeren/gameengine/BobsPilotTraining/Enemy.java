package dk.andreas.soeren.gameengine.BobsPilotTraining;

/**
 * Created by Andreas on 14/11/2017.
 */

public class Enemy
{
    public int WIDTH;
    public int HEIGHT;

    public int x = 319 - WIDTH;
    public int y = 0;

    public int vx = -150;
    public int vy = 150;

    public Enemy(int WIDTH, int HEIGHT, int x, int y)
    {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;

        this.x = x;
        this.y = y;
    }
}

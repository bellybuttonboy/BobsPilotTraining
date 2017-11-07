package dk.andreas.soeren.gameengine.MyGame;

import android.graphics.Bitmap;

/**
 * Created by Andreas on 13-Oct-17.
 */

public class World
{
    float passedTime = 0;

    public static final float MIN_X = 5;
    public static final float MAX_X = 315;
    public static final float MIN_Y = 5;
    public static final float MAX_Y = 475;
    Froggy froggy = new Froggy();
    LilyPad padOne = new LilyPad(62, 75, 52);
    LilyPad padTwo = new LilyPad(124, 122, 99);
    LilyPad padThree = new LilyPad(248, 167, 146);

    public void update(float deltatime, String direction)
    {
        moveLilyPads(deltatime);

        if (direction.equals("down"))
        {
            froggy.y += 47;
            froggy.onRightPad = false;
            froggy.onLeftPad = false;
        }
        else if (direction.equals("left"))
        {
            froggy.x -= 31;
        }
        else if (direction.equals("right"))
        {
            froggy.x += 31;
        }



        checkIfOnPad(padOne);
     //   checkIfOnPad(padTwo);
       // checkIfOnPad(padThree);

        checkForOutOfBounds(froggy);
        checkForOutOfBounds(padOne);
        checkForOutOfBounds(padTwo);
        checkForOutOfBounds(padThree);
    }
    public void checkIfOnPad(LilyPad pad)
    {
        if (froggy.y == pad.frogComparisonY && froggy.x == pad.x)
        {
            froggy.onLeftPad = true;
        }
        if (froggy.y == pad.frogComparisonY && froggy.x == pad.x + 31)
        {
            froggy.onRightPad = true;
        }
        if (froggy.onLeftPad)
        {
            froggy.x = pad.x;
        }
        if (froggy.onRightPad)
        {
            froggy.x = pad.x + 31;
        }
    }

    public void moveLilyPads(float deltatime)
    {
        passedTime = passedTime + deltatime;
        if (passedTime - (int)passedTime > 0.8f)
        {
            padOne.x += 31;
            padTwo.x -= 31;
            padThree.x += 31;
            passedTime = 0;
        }
    }

    public void checkForOutOfBounds(LilyPad pad)
    {
        if (pad.x < MIN_X)
        {
            pad.x = (int)MAX_X - 62;
        }

        if (pad.x > MAX_X - 62)
        {
            pad.x = (int)MIN_X;
        }
    }

    public void checkForOutOfBounds(Froggy frog)
    {
        if (frog.x < MIN_X)
        {
            frog.x = (int)MAX_X - 31;
        }

        if (frog.x > MAX_X - 31)
        {
            frog.x = (int)MIN_X;
        }

        if (frog.y > MAX_Y - 47)
        {
            frog.y = (int)MIN_Y;
        }
    }

}

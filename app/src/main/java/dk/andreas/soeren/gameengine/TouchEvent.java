package dk.andreas.soeren.gameengine;

/**
 * Created by Søren on 12-09-2017.
 */

public class TouchEvent
{
    public enum TouchEventType
    {
        Down,
        Up,
        Dragged
    }

    public TouchEventType type; //The type of the event
    public int x;               //The x-coordinate of the event
    public int y;               //The y-coordinate of the event
    public int pointer;         //The pointer id(from Android system)
}

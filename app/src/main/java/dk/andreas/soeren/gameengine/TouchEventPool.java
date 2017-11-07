package dk.andreas.soeren.gameengine;

/**
 * Created by Søren on 12-09-2017.
 */

public class TouchEventPool  extends Pool<TouchEvent>
{
    @Override
    protected TouchEvent newItem()
    {
        return new TouchEvent();
    }
}

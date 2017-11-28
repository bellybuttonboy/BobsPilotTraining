package dk.andreas.soeren.gameengine.BobsPilotTraining;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Andreas on 28-Nov-17.
 */

public class Highscores
{
    SharedPreferences sharedPreferences;
    String[] highscores = new String[3];

    public void loadRecords(Context context)
    {
        sharedPreferences = context.getSharedPreferences("DATA", Context.MODE_PRIVATE);
        highscores[0] = sharedPreferences.getString("first", "0");
        highscores[1] = sharedPreferences.getString("second", "0");
        highscores[2] = sharedPreferences.getString("third","0");

    }

    public void updateRecords(int time)
    {
        if (time >= Integer.parseInt(highscores[0]))
        {
            sharedPreferences.edit().putString("first","" + time).apply();
            sharedPreferences.edit().putString("second", highscores[0]).apply();
            sharedPreferences.edit().putString("third", highscores[1]).apply();
        }
        else if (time >= Integer.parseInt(highscores[1]))
        {
            sharedPreferences.edit().putString("second", "" + time).apply();
            sharedPreferences.edit().putString("third", highscores[1]).apply();
        }
        else if (time >= Integer.parseInt(highscores[2]))
        {
            sharedPreferences.edit().putString("third","" + time).apply();
        }
    }
}

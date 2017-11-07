package dk.andreas.soeren.gameengine;

import android.media.SoundPool;

/**
 * Created by Søren on 26-09-2017.
 */

public class Sound
{
    int soundId;
    SoundPool soundPool;

    public Sound(SoundPool sp, int sId)
    {
        this.soundId = sId;
        this.soundPool = sp;
    }

    public void play(float volume)
    {
        soundPool.play(soundId, volume, volume, 0, 0, 1);
    }

    public void disposed()
    {
        soundPool.unload(soundId);
    }
}

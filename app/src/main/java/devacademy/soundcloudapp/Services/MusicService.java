package devacademy.soundcloudapp.Services;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

/**
 * Created by teodor on 11.03.2016.
 */
public class MusicService extends Service implements MediaPlayer.OnPreparedListener{

    private static final String TAG = "MUSIC_SERVICE";
    private MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getExtras() != null)  {
            player = new MediaPlayer();
            player.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
            //player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setOnPreparedListener(this);
            try {
                Log.e(TAG, intent.getStringExtra("songUrl"));
                player.setDataSource(intent.getExtras().getString("songUrl"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.prepareAsync();
        } else {
            Log.e(TAG, "INTENT extras null");
        }
        return START_STICKY;
    }


    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }
}

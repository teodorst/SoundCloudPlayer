package devacademy.soundcloudapp.Services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.MediaSessionManager;
import android.os.IBinder;
import android.os.PowerManager;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.io.IOException;

import devacademy.soundcloudapp.Activities.SearchActivity;
import devacademy.soundcloudapp.Activities.SongActivity;
import devacademy.soundcloudapp.R;

/**
 * Created by teodor on 11.03.2016.
 */
public class MusicService extends Service implements MediaPlayer.OnPreparedListener{

    private static final String TAG = "MUSIC_SERVICE";
    public static boolean IS_SERVICE_RUNNING = false;
    private MediaPlayer player;
    private Notification notification;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {


        if(intent != null && intent.getExtras() != null)  {

            if (intent.getAction().equals("PLAY_FROM_APP")) {
                if(player!= null && player.isPlaying()) {
                    player.stop();
                    player.release();
                }
                player = new MediaPlayer();
                player.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                player.setOnPreparedListener(this);
                try {
                    Log.e(TAG, intent.getStringExtra("songUrl"));
                    String url = intent.getExtras().getString("songUrl")+"?client_id=3e7f2924c47462bf79720ae5995194de";
                    player.setDataSource(url.replace("https", "http"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                player.prepareAsync();
            } else if(intent.getAction().equals("PLAY_FROM_NOTIF") && player != null) {
                player.start();
            } else if(intent.getAction().equals("PAUSE_FROM_NOTIF") && player != null) {
                player.pause();
            }
            else if(intent.getAction().equals("STOP_FROM_NOTIF") && player != null) {
                player.start();
                player.release();
            }
        } else {
            Log.e(TAG, "INTENT extras null");
        }
        return START_STICKY;
    }


    @Override
    public void onPrepared(MediaPlayer mp) {
        showNotification();
        mp.start();
    }

    public void showNotification() {
        Intent playIntent = new Intent(this, MusicService.class);
        playIntent.setAction("PLAY_FROM_NOTIF");
        PendingIntent play = PendingIntent.getService(this, 0, playIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent pauseIntent = new Intent(this, MusicService.class);
        playIntent.setAction("PAUSE_FROM_NOTIF");
        PendingIntent pause = PendingIntent.getService(this, 0, pauseIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        Intent stopIntent = new Intent(this, SearchActivity.class);
        playIntent.setAction("STOP_FROM_NOTIF");
        PendingIntent stop = PendingIntent.getService(this, 0, stopIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("TutorialsFace Music Player")
                .setTicker("Music Player")
                .setContentText("My song")
                .setOngoing(true)
                .addAction(android.R.drawable.ic_media_pause, "Pause",
                        pause)
                .addAction(android.R.drawable.ic_media_play, "Play",
                        play)
                .addAction(android.R.drawable.ic_media_next, "Stop",
                        stop).build();
        startForeground(354, notification);
    }

}

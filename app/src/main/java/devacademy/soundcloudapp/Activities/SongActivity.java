package devacademy.soundcloudapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import devacademy.soundcloudapp.R;
import devacademy.soundcloudapp.Services.MusicService;
import devacademy.soundcloudapp.SoundCloud.SoundCloudRequestManager;
import devacademy.soundcloudapp.SoundCloudDataModel.Song;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongActivity extends AppCompatActivity {
    private static final String TAG = "SongActivity";
    private Song currentSong;
    private SoundCloudRequestManager soundCloudApi;

    private TextView songTitleTextView, userNameTextView, timeTextView, downloadableTextView;
    private TextView numberOfLikesTextView;
    private String songId;
    private Button playSongButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if( getIntent() != null )
            songId = getIntent().getExtras().getString("songId");

        setContentView(R.layout.activity_song);
        soundCloudApi = SoundCloudRequestManager.getInstance();

        //components
        songTitleTextView = (TextView) findViewById(R.id.song_title);
        userNameTextView = (TextView) findViewById(R.id.user_name);
        timeTextView = (TextView) findViewById(R.id.song_time);
        downloadableTextView = (TextView) findViewById(R.id.song_streamable);
        numberOfLikesTextView = (TextView) findViewById(R.id.song_likes);
        playSongButton = (Button) findViewById(R.id.play_song_button);

        if( songId != null )
            soundCloudApi.getSong(songId, new Callback<Song>() {
                @Override
                public void onResponse(Call<Song> call, Response<Song> response) {
                    currentSong = response.body();

                    int duration = Integer.parseInt(currentSong.getTime());
                    int seconds = duration / 1000;
                    int minutes= seconds / 60;
                    seconds -= minutes * 60;
                    currentSong.setTime(minutes + ":" + seconds);

                    songTitleTextView.setText(currentSong.getTitle());
                    userNameTextView.setText(currentSong.getUser().getUserName());
                    timeTextView.setText(currentSong.getTime());
                    downloadableTextView.setText(currentSong.getStreamable().toString());
                    numberOfLikesTextView.setText(currentSong.getNumberOfLikes());
                    Log.e(TAG, currentSong.getStreamableUrl());
                }

                @Override
                public void onFailure(Call<Song> call, Throwable t) {

                }
            });

        playSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SongActivity.this, MusicService.class);
                intent.putExtra("songUrl", currentSong.getStreamableUrl());
                startService(intent);
            }
        });
    }




}

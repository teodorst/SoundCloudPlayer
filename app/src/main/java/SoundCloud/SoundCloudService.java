package SoundCloud;

import android.util.Log;

import java.util.List;

import devacademy.soundcloudapp.Song;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by teodor on 09.03.2016.
 */
public class SoundCloudService {

    private SoundCloudAPI soundCloudAPI = null;
    private static SoundCloudService instance = null;
    private final String client_id = "3e7f2924c47462bf79720ae5995194de";
    private static final String TAG = "SOUNDCLOUDSERVICE";
    private SoundCloudService(String baseUrl) {
        init(baseUrl);
    }


    private void init(String baseUrl) {
        Retrofit retrofitInstance = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .build();

        this.soundCloudAPI = retrofitInstance.create(SoundCloudAPI.class);

    }

    public static SoundCloudService getInstance() {
        if( instance == null ) {
            instance = new SoundCloudService("https://api..com");
        }
        return instance;
    }


    public void getSongs (String singerName, Callback<List<Song>> cb) {

        Call<List<Song>> songsCall = soundCloudAPI.searchSongs(singerName, client_id);
        songsCall.enqueue(cb);
    }

}

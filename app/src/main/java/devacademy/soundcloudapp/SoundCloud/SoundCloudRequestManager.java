package devacademy.soundcloudapp.SoundCloud;

import java.util.List;

import devacademy.soundcloudapp.SoundCloudDataModel.Song;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by teodor on 09.03.2016.
 */
public class SoundCloudRequestManager {

    private SoundCloudAPI soundCloudAPI = null;
    private static SoundCloudRequestManager instance = null;
    private final String client_id = "3e7f2924c47462bf79720ae5995194de";
    private static final String TAG = "SOUNDCLOUDSERVICE";
    private SoundCloudRequestManager(String baseUrl) {
        init(baseUrl);
    }


    private void init(String baseUrl) {
        Retrofit retrofitInstance = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.soundCloudAPI = retrofitInstance.create(SoundCloudAPI.class);

    }

    public static SoundCloudRequestManager getInstance() {
        if( instance == null ) {
            instance = new SoundCloudRequestManager("http://api.soundcloud.com");
        }
        return instance;
    }


    public void getSongs(String singerName, Callback<List<Song>> cb) {
        Call<List<Song>> call = soundCloudAPI.searchSongs(singerName, client_id);

        call.enqueue(cb);
    }

    public void getSong(String songId, Callback<Song> cb) {
        Call<Song> songCall = soundCloudAPI.getSong(songId, client_id);

        songCall.enqueue(cb);
    }

}

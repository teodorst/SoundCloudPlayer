package SoundCloud;

import java.util.List;

import javax.security.auth.callback.Callback;

import devacademy.soundcloudapp.Song;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;



public interface SoundCloudAPI {


    @GET("tracks") //tracks?q=Elvis&client_id=3e7f2924c47462bf79720ae5995194de
    Call<List<Song>> searchSongs (@Query("q") String singer, @Query("client_id") String client_id);

    @GET("tracks/{songId}")
    Call<Song> getSong(@Path("songId") int songId, @Query("client_id") String client_id);


}

package devacademy.soundcloudapp.SoundCloud;

import java.util.List;

import devacademy.soundcloudapp.SoundCloudDataModel.Song;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;



public interface SoundCloudAPI {


    @GET("tracks") //tracks?q=Elvis&client_id=3e7f2924c47462bf79720ae5995194de
    Call<List<Song>> searchSongs (@Query("q") String singer, @Query("client_id") String client_id);

    @GET("tracks/{songId}")
    Call<Song> getSong(@Path("songId") String songId, @Query("client_id") String client_id);


}

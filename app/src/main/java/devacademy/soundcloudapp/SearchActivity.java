package devacademy.soundcloudapp;

import android.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import SoundCloud.SoundCloudService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    private EditText searchEditText;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchEditText = (EditText) findViewById(R.id.search_edit_text);
        searchButton = (Button) findViewById(R.id.search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle toListArgs = new Bundle();
                toListArgs.putString("singer", searchEditText.getText().toString());
                ListFragment listFragment = new ListFragment();
                listFragment.setArguments(toListArgs);
            }
        });
    }
}

/*
new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String singerName = searchEditText.getText().toString();
                soundCloudServiceServiceInstance.getSongs(singerName, new Callback<List<Song>>() {
                    @Override
                    public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                        //song = response;
                        Log.e(TAG, "ON SUCCESS: Call " + call.toString());
                        Log.e(TAG, "ON SUCCESS: Response" + response.toString());
                        //Log.e(TAG, "Call " + call.toString());
                    }

                    @Override
                    public void onFailure(Call<List<Song>> call, Throwable t) {
                        Log.e(TAG, "ON FAIL: Call " + call.toString());

                    }
                });
            }
        }
 */
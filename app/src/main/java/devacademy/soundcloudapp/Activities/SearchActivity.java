package devacademy.soundcloudapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import devacademy.soundcloudapp.R;
import devacademy.soundcloudapp.Fragments.SongListFragment;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    private EditText searchEditText;
    private Button searchButton;
    private SongListFragment listFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchEditText = (EditText) findViewById(R.id.search_edit_text);
        searchButton = (Button) findViewById(R.id.search_button);
        listFragment = null;


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (findViewById(R.id.song_list_container) != null) {
                   if (listFragment != null)
                       listFragment.updateListForSinger(searchEditText.getText().toString());
                   else {
                       listFragment = new SongListFragment();
                       Bundle toListArgs = new Bundle();

                       toListArgs.putString("singer", searchEditText.getText().toString());
                       listFragment.setArguments(toListArgs);
                       getFragmentManager().beginTransaction().add(
                               R.id.song_list_container,
                               listFragment
                       ).commit();
                   }
                }
            }
        });
    }
}


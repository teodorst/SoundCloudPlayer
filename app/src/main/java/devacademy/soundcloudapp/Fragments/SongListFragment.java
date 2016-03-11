package devacademy.soundcloudapp.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

import devacademy.soundcloudapp.Activities.SongActivity;
import devacademy.soundcloudapp.Adapters.SongArrayListAdapter;
import devacademy.soundcloudapp.SoundCloud.SoundCloudRequestManager;
import devacademy.soundcloudapp.SoundCloudDataModel.Song;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SongListFragment extends android.app.ListFragment {

    private SoundCloudRequestManager soundCloudServiceRequestManagerInstance;
    private SongArrayListAdapter listAdapter;
    private List<Song> songsList;
    private static final String TAG = "SongListFragment";

    public void onActivityCreated(Bundle savedInstance) {
        super.onActivityCreated(savedInstance);

        soundCloudServiceRequestManagerInstance = SoundCloudRequestManager.getInstance();
        songsList = new LinkedList<Song>();
        listAdapter = new SongArrayListAdapter(getActivity(), android.R.layout.activity_list_item, songsList);
        setListAdapter(listAdapter);
        updateListForSinger(getArguments().getString("singer"));
    }

    public void updateListForSinger(String singerName) {
        soundCloudServiceRequestManagerInstance.getSongs(singerName, new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                Log.e(TAG, "ON SUCCESS: Call " + call.toString());
                Log.e(TAG, "ON SUCCESS: Response" + response.body().toString());
                songsList.clear();
                for (Song s : response.body()) {
                    int duration = Integer.parseInt(s.getTime());
                    int seconds = duration / 1000;
                    int minutes= seconds / 60;
                    seconds -= minutes * 60;
                    s.setTime(minutes + ":" + seconds);
                    songsList.add(s);
                }
                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                Log.e(TAG, "ON FAIL: Call " + call.toString());
            }
        });
    }

    @Override
    public void onListItemClick( ListView l, View v, int position, long id ) {
        Intent intent = new Intent(getActivity(), SongActivity.class);
        intent.putExtra("songId", v.getTag().toString());
        startActivity(intent);
    }

}

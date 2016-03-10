package devacademy.soundcloudapp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

import SoundCloud.SoundCloudService;


public class SongListFragment extends android.app.ListFragment {

    private SoundCloudService soundCloudServiceServiceInstance;
    private List<Song> songsList;

    public void onActivityCreated(Bundle savedInstance) {
        super.onActivityCreated(savedInstance);

        soundCloudServiceServiceInstance = SoundCloudService.getInstance();

        List<Song> songsList = new LinkedList<Song>();
        songsList.add(new Song("dasfas", "adsfdsa", "dsafdas"));

        //set adapter
        setListAdapter(new SongArrayListAdapter(getActivity(), android.R.layout.activity_list_item, songsList));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String strtext = getArguments().getString("edttext");
        return inflater.inflate(R.layout.fragment, container, false);
    }


    @Override
    public void onListItemClick( ListView l, View v, int position, long id ) {

    }


}

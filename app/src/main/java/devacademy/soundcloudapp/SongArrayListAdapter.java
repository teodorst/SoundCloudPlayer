package devacademy.soundcloudapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by teodor on 10.03.2016.
 */
public class SongArrayListAdapter extends ArrayAdapter<Song> {

    private Context context;
    private List<Song> songs;

    static class ViewHolderItem {
        TextView name, singer, time;
    }

    public SongArrayListAdapter(Context context, int textViewResouceId, List<Song> songs) {
        super(context, textViewResouceId, songs);
        this.context = context;
        this.songs = songs;
    }

    public View getView(int position, View convertView, ViewGroup parrent) {
        Song song = songs.get(position);
        ViewHolderItem listItemHolder;

        if( convertView == null ) {
            listItemHolder = new ViewHolderItem();

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.song_list_item, parrent, false);
            listItemHolder = new ViewHolderItem();
            listItemHolder.name = (TextView) convertView.findViewById(R.id.list_song_name);
            listItemHolder.singer = (TextView) convertView.findViewById(R.id.list_song_singer);
            listItemHolder.time = (TextView) convertView.findViewById(R.id.list_song_time);
            convertView.setTag(listItemHolder);
        } else {
            listItemHolder = (ViewHolderItem) convertView.getTag();
        }

        listItemHolder.name.setText(song.getName());
        listItemHolder.singer.setText(song.getSinger());
        listItemHolder.time.setText(song.getTime());
        return convertView;
    }
}

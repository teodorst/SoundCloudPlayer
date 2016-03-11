package devacademy.soundcloudapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import devacademy.soundcloudapp.R;
import devacademy.soundcloudapp.SoundCloudDataModel.Song;

/**
 * Created by teodor on 10.03.2016.
 */
public class SongArrayListAdapter extends ArrayAdapter<Song> {

    private Context context;
    private List<Song> songs;

    static class ViewHolderItem {
        TextView nameTextView, timeTextView, userNameTextView;
        String songId;
        public String toString() {
            return songId;
        }
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
            listItemHolder.nameTextView = (TextView) convertView.findViewById(R.id.list_song_name);
            listItemHolder.timeTextView = (TextView) convertView.findViewById(R.id.list_song_time);
            listItemHolder.userNameTextView = (TextView) convertView.findViewById(R.id.list_song_user);
            convertView.setTag(listItemHolder);
        } else {
            listItemHolder = (ViewHolderItem) convertView.getTag();
        }

        listItemHolder.nameTextView.setText(song.getTitle());
        listItemHolder.timeTextView.setText(song.getTime());
        listItemHolder.userNameTextView.setText(song.getUser().getUserName());
        listItemHolder.songId = song.getId();
        return convertView;
    }
}

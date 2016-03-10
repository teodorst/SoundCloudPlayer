package devacademy.soundcloudapp;

/**
 * Created by teodor on 09.03.2016.
 */
public class Song {
    private String name, singer, streamableUrl, time;

    public Song (String name, String singer, String streamableUrl) {
        this.name = name;
        this.singer = singer;
        this.streamableUrl = streamableUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getStreamableUrl() {
        return streamableUrl;
    }

    public void setStreamableUrl(String streamableUrl) {
        this.streamableUrl = streamableUrl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

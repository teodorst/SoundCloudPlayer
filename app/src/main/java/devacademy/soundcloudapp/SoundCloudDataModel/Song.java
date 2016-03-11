package devacademy.soundcloudapp.SoundCloudDataModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by teodor on 09.03.2016.
 */
public class Song implements Serializable {

    @SerializedName("title")
    private String title;

    @SerializedName("stream_url")
    private String streamableUrl;

    @SerializedName("duration")
    private String time;


    @SerializedName("id")
    private String id;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("likes_count")
    private String numberOfLikes;

    @SerializedName("user")
    private User user;

    @SerializedName("streamable")
    private Boolean streamable;

    public Song (String title, String streamableUrl) {
        this.title = title;
        this.streamableUrl = streamableUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(String numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Boolean getStreamable() {
        return streamable;
    }

    public void setStreamable(Boolean streamable) {
        this.streamable = streamable;
    }
}

package devacademy.soundcloudapp.SoundCloudDataModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by teodor on 10.03.2016.
 */

public class User implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("username")
    private String userName;

    @SerializedName("kind")
    private String type;

    @SerializedName("avatar_url")
    private String avatarUrl;

    public User(String id, String userName, String type, String avatarUrl) {
        this.id = id;
        this.userName = userName;
        this.type = type;
        this.avatarUrl = avatarUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}

package android.andela.com.kenyajavadevs.model;

import com.google.gson.annotations.SerializedName;

public class GithubUser {

    @SerializedName("login")
    private String username;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("url")
    private String url;

    @SerializedName("name")
    private String name;

    public String getUsername() {
        return username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }
}

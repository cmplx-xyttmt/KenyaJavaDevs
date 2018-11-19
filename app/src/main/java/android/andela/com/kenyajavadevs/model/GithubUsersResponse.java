package android.andela.com.kenyajavadevs.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GithubUsersResponse {

    @SerializedName("")
    private List<GithubUser> githubUsers;

    public GithubUsersResponse() {
        githubUsers = new ArrayList<>();
    }
}

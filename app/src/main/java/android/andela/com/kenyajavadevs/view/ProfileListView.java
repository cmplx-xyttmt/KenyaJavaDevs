package android.andela.com.kenyajavadevs.view;

import android.andela.com.kenyajavadevs.model.GithubUser;

import java.util.List;

public interface ProfileListView {

    void usersListReady(List<GithubUser> githubUsers);

    void onResponseFailure(Throwable throwable);
}

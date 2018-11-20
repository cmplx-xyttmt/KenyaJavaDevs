package android.andela.com.kenyajavadevs.presenter;

import android.andela.com.kenyajavadevs.model.GithubUsersResponse;
import android.andela.com.kenyajavadevs.service.GithubApi;
import android.andela.com.kenyajavadevs.service.GithubService;
import android.andela.com.kenyajavadevs.view.ProfileListView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GithubPresenter {
    private ProfileListView profileListView;
    private GithubApi githubApi;

    public GithubPresenter(ProfileListView view) {
        this.profileListView = view;
        githubApi = GithubService.buildService(GithubApi.class);
    }

    public void getUsers() {
        final Call<GithubUsersResponse> usersRequest = githubApi.getUsers("location:Kenya+language:java");

        usersRequest.enqueue(new Callback<GithubUsersResponse>() {
            @Override
            public void onResponse(Call<GithubUsersResponse> call, Response<GithubUsersResponse> response) {
                profileListView.usersListReady(response.body().getGithubUsers());
            }

            @Override
            public void onFailure(Call<GithubUsersResponse> call, Throwable t) {
                profileListView.onResponseFailure(t);
            }
        });
    }
}

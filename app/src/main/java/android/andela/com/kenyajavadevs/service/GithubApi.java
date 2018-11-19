package android.andela.com.kenyajavadevs.service;

import android.andela.com.kenyajavadevs.model.GithubUser;
import android.andela.com.kenyajavadevs.model.GithubUsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubApi {

    @GET("users")
    Call<List<GithubUsersResponse>> getUsers(@Query(value = "q", encoded = true)String query);

    @GET("users/{username}")
    Call<GithubUser> getUser(@Path("username")String username);
}

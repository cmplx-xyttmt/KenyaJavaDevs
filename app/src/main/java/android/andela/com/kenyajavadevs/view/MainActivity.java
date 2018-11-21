package android.andela.com.kenyajavadevs.view;

import android.andela.com.kenyajavadevs.R;
import android.andela.com.kenyajavadevs.adapter.GithubUsersAdapter;
import android.andela.com.kenyajavadevs.model.GithubUser;
import android.andela.com.kenyajavadevs.presenter.GithubPresenter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ProfileListView {
    public static final String EXTRA_USERNAME = "android.andela.com.kenyajavadevs.extra.USERNAME";
    public static final String EXTRA_PROFILE_LINK = "android.andela.com.kenyajavadevs.extra.PROFILE_LINK";
    // TODO: Also put an extra for the profile image.

    LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayoutManager = new LinearLayoutManager(this);

        GithubPresenter presenter = new GithubPresenter(this);
        presenter.getUsers();
    }


    @Override
    public void usersListReady(List<GithubUser> githubUsers) {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(new GithubUsersAdapter(this, githubUsers));
        recyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(this,
                "Something went wrong: " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
    }
}

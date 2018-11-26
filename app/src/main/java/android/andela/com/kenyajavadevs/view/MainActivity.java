package android.andela.com.kenyajavadevs.view;

import android.andela.com.kenyajavadevs.R;
import android.andela.com.kenyajavadevs.adapter.GithubUsersAdapter;
import android.andela.com.kenyajavadevs.model.GithubUser;
import android.andela.com.kenyajavadevs.presenter.GithubPresenter;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ProfileListView {
    public static final String LIST_STATE_KEY = "users_list_state";
    public static final String EXTRA_USERNAME = "android.andela.com.kenyajavadevs.extra.USERNAME";
    public static final String EXTRA_PROFILE_LINK = "android.andela.com.kenyajavadevs.extra.PROFILE_LINK";
    public static final String EXTRA_PROFILE_IMAGE_LINK = "android.andela.com.kenyajavadevs.extra.PROFILE_IMAGE_LINK";

    Parcelable githubUsersListState;
    GridLayoutManager mGridLayoutManager;
    SwipeRefreshLayout mSwipeRefreshLayout;
    ProgressBar mProgressBar;
    GithubPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridLayoutManager = new GridLayoutManager(this, 2);
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh);
        mProgressBar = findViewById(R.id.progress_bar);

        mPresenter = new GithubPresenter(this);
        loadUsers();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadUsers();
            }
        });
    }

    private void loadUsers() {
        mProgressBar.setVisibility(ProgressBar.VISIBLE);
        mPresenter.getUsers();
    }

    private void finishLoadingUsers() {
        mSwipeRefreshLayout.setRefreshing(false);
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);
    }


    @Override
    public void usersListReady(List<GithubUser> githubUsers) {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(new GithubUsersAdapter(this, githubUsers));
        recyclerView.setLayoutManager(mGridLayoutManager);
        finishLoadingUsers();
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(this,
                "Something went wrong: " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
        finishLoadingUsers();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        githubUsersListState = mGridLayoutManager.onSaveInstanceState();
        outState.putParcelable(LIST_STATE_KEY, githubUsersListState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null)
            githubUsersListState = savedInstanceState.getParcelable(LIST_STATE_KEY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (githubUsersListState != null) {
            mGridLayoutManager.onRestoreInstanceState(githubUsersListState);
        }
    }
}

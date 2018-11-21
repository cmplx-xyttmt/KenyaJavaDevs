package android.andela.com.kenyajavadevs.view;

import android.andela.com.kenyajavadevs.R;
import android.andela.com.kenyajavadevs.adapter.GithubUsersAdapter;
import android.andela.com.kenyajavadevs.model.GithubUser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_USERNAME = "android.andela.com.kenyajavadevs.extra.USERNAME";
    public static final String EXTRA_PROFILE_LINK = "android.andela.com.kenyajavadevs.extra.PROFILE_LINK";
    // TODO: Also put an extra for the profile image.

    private final ArrayList<GithubUser> sampleUsers = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private GithubUsersAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerview);
        mAdapter = new GithubUsersAdapter(this, sampleUsers);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

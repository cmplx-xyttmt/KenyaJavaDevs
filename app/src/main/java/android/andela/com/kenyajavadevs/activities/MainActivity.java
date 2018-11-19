package android.andela.com.kenyajavadevs.activities;

import android.andela.com.kenyajavadevs.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_USERNAME = "android.andela.com.kenyajavadevs.extra.USERNAME";
    public static final String EXTRA_PROFILE_LINK = "android.andela.com.kenyajavadevs.extra.PROFILE_LINK";
    // TODO: Also put an extra for the profile image.

    private final LinkedList<String> sampleUsernames = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private ProfileListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting up the sample data
        for (int i = 0; i < 20; i++) sampleUsernames.addLast("Sample User " + (i + 1));

        mRecyclerView = findViewById(R.id.recyclerview);
        mAdapter = new ProfileListAdapter(this, sampleUsernames);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

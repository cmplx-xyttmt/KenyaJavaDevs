package android.andela.com.kenyajavadevs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
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

package android.andela.com.kenyajavadevs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView usernameTextView = findViewById(R.id.profile_username);
        TextView profileLinkTextView = findViewById(R.id.profile_link);

        Intent intent = getIntent();

        String username = intent.getStringExtra(MainActivity.EXTRA_USERNAME);
        usernameTextView.setText(username);

        String profileLink = intent.getStringExtra(MainActivity.EXTRA_PROFILE_LINK);
        profileLinkTextView.setText(profileLink);
    }
}

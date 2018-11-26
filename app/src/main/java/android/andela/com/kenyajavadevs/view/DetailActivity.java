package android.andela.com.kenyajavadevs.view;

import android.andela.com.kenyajavadevs.R;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView usernameTextView = findViewById(R.id.profile_username);
        TextView profileLinkTextView = findViewById(R.id.profile_link);
        ImageView profileImageView = findViewById(R.id.profile_image);

        Intent intent = getIntent();

        final String username = intent.getStringExtra(MainActivity.EXTRA_USERNAME);
        usernameTextView.setText(username);

        final String profileLink = intent.getStringExtra(MainActivity.EXTRA_PROFILE_LINK);
        profileLinkTextView.setText(profileLink);

        String profileImageLink = intent.getStringExtra(MainActivity.EXTRA_PROFILE_IMAGE_LINK);
        Picasso.get().load(profileImageLink).into(profileImageView);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, "Checkout this awesome developer @" + username + ", " + profileLink + ".");
                startActivity(Intent.createChooser(sharingIntent, "Share developer using: "));
            }
        });
    }
}

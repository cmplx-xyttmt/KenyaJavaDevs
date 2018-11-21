package android.andela.com.kenyajavadevs.adapter;

import android.andela.com.kenyajavadevs.R;
import android.andela.com.kenyajavadevs.model.GithubUser;
import android.andela.com.kenyajavadevs.view.DetailActivity;
import android.andela.com.kenyajavadevs.view.MainActivity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class GithubUsersAdapter extends RecyclerView.Adapter<GithubUsersAdapter.ProfileViewHolder> {
    private final ArrayList<GithubUser> mProfileList;
    private LayoutInflater mInflator;

    public GithubUsersAdapter(Context context, List<GithubUser> profileList) {
        mInflator = LayoutInflater.from(context);
        mProfileList = (ArrayList<GithubUser>) profileList;
    }


    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View mProfileView = mInflator.inflate(R.layout.profile_list_item, parent, false);
        return new ProfileViewHolder((LinearLayout) mProfileView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProfileViewHolder holder, int position) {
        GithubUser profile = mProfileList.get(position);
        Picasso.get().load(profile.getAvatarUrl()).into(holder.mProfileImage);
        holder.mProfileUsername.setText(profile.getUsername());

        holder.mProfileItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                String username = mProfileList.get(holder.getAdapterPosition()).getUsername();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(MainActivity.EXTRA_USERNAME, username);
                intent.putExtra(MainActivity.EXTRA_PROFILE_LINK, "github.com/" + username);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProfileList.size();
    }

    class ProfileViewHolder extends RecyclerView.ViewHolder {
        final ImageView mProfileImage;
        final TextView mProfileUsername;
        final GithubUsersAdapter mAdapter;
        final LinearLayout mProfileItemView;

        ProfileViewHolder(LinearLayout mProfileItemView, GithubUsersAdapter adapter) {
            super(mProfileItemView);
            mProfileImage = mProfileItemView.findViewById(R.id.list_profile_image);
            mProfileUsername = mProfileItemView.findViewById(R.id.list_username);
            mAdapter = adapter;
            this.mProfileItemView = mProfileItemView;
        }
    }
}

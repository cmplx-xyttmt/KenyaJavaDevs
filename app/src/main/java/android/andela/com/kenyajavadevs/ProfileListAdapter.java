package android.andela.com.kenyajavadevs;

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

import java.util.LinkedList;
import java.util.List;

public class ProfileListAdapter extends RecyclerView.Adapter<ProfileListAdapter.ProfileViewHolder> {
    private final LinkedList<String> mProfileList;
    private LayoutInflater mInflator;

    ProfileListAdapter(Context context, List<String> profileList) {
        mInflator = LayoutInflater.from(context);
        mProfileList = (LinkedList<String>) profileList;
    }


    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View mProfileView = mInflator.inflate(R.layout.profile_list_item, parent, false);
        return new ProfileViewHolder((LinearLayout) mProfileView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProfileViewHolder holder, int position) {
        String username = mProfileList.get(position);
        holder.mProfileImage.setImageResource(R.drawable.profile_pic);
        holder.mProfileUsername.setText(username);

        holder.mProfileItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                String username = mProfileList.get(holder.getAdapterPosition());
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
        final ProfileListAdapter mAdapter;
        final LinearLayout mProfileItemView;

        ProfileViewHolder(LinearLayout mProfileItemView, ProfileListAdapter adapter) {
            super(mProfileItemView);
            mProfileImage = mProfileItemView.findViewById(R.id.list_profile_image);
            mProfileUsername = mProfileItemView.findViewById(R.id.list_username);
            mAdapter = adapter;
            this.mProfileItemView = mProfileItemView;
        }
    }
}

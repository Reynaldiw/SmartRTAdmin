package com.reynaldiwijaya.smartrtadmin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.reynaldiwijaya.smartrtadmin.Model.User.UserItem;
import com.reynaldiwijaya.smartrtadmin.R;
import com.reynaldiwijaya.smartrtadmin.Utills.Constants;
import com.reynaldiwijaya.smartrtadmin.View.DetailUserActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private final Context context;
    private final List<UserItem> userItemList;

    public UserAdapter(Context context, List<UserItem> userItemList) {
        this.context = context;
        this.userItemList = userItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_user, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final UserItem userItem = userItemList.get(i);

        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.avatar)
                .error(R.drawable.avatar);
        Glide.with(context)
                .load(Constants.IMAGE_USER_URL + userItem.getFoto())
                .apply(requestOptions)
                .into(viewHolder.imgProfile);

        viewHolder.tvName.setText(userItem.getNamaLengkap());
        viewHolder.tvStatus.setText(userItem.getLevel());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.NAMA, userItem.getNamaLengkap());
                bundle.putString(Constants.ID, userItem.getIdUser());
                bundle.putString(Constants.NO_KTP, userItem.getNoKtp());
                bundle.putString(Constants.ALAMAT, userItem.getAlamat());
                bundle.putString(Constants.STATUS, userItem.getStatus());
                bundle.putString(Constants.DATE, userItem.getTglLahir());
                bundle.putString(Constants.JENKEL, userItem.getJenkel());
                bundle.putString(Constants.PROFESI, userItem.getProfesi());
                bundle.putString(Constants.NO_TLP, userItem.getNoTlp());
                bundle.putString(Constants.EMAIL, userItem.getEmail());
                bundle.putString(Constants.FOTO, userItem.getFoto());
                bundle.putString(Constants.LEVEL, userItem.getLevel());

                context.startActivity(new Intent(context, DetailUserActivity.class).putExtras(bundle));
            }
        });
    }

    @Override
    public int getItemCount() {
        return userItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_profile)
        CircleImageView imgProfile;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

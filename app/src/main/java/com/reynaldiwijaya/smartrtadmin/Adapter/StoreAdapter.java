package com.reynaldiwijaya.smartrtadmin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.reynaldiwijaya.smartrtadmin.Model.Store.StoreItem;
import com.reynaldiwijaya.smartrtadmin.R;
import com.reynaldiwijaya.smartrtadmin.Utills.Constants;
import com.reynaldiwijaya.smartrtadmin.View.DetailStoreActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {
    private Context context;
    private List<StoreItem> storeItemList;

    public StoreAdapter(Context context, List<StoreItem> storeItemList) {
        this.context = context;
        this.storeItemList = storeItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_store, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final StoreItem storeItem = storeItemList.get(i);

        viewHolder.tvNama.setText(storeItem.getNamaToko());
        RequestOptions options = new RequestOptions().error(R.drawable.ic_broken_image_black_24dp);
        Glide.with(context)
                .load(Constants.IMAGE_STORE_URL + storeItem.getFoto_toko())
                .apply(options)
                .into(viewHolder.imgStore);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.NAMA_TOKO, storeItem.getNamaToko());
                bundle.putString(Constants.DESKRIPSI, storeItem.getDeskripsi());
                bundle.putString(Constants.ID, storeItem.getIdToko());
                bundle.putString(Constants.ALAMAT, storeItem.getAlamatToko());
                bundle.putString(Constants.FOTO, storeItem.getFoto());
                bundle.putString(Constants.NO_TLP, storeItem.getNoTlp());
                bundle.putString(Constants.NAMA, storeItem.getNama());
                bundle.putString(Constants.NO_TLP_TOKO, storeItem.getNo_tlp_store());
                bundle.putString(Constants.FOTO_TOKO, storeItem.getFoto_toko());

                context.startActivity(new Intent(context, DetailStoreActivity.class).putExtras(bundle));
            }
        });
    }

    @Override
    public int getItemCount() {
        return storeItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_nama)
        TextView tvNama;
        @BindView(R.id.img_store)
        ImageView imgStore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

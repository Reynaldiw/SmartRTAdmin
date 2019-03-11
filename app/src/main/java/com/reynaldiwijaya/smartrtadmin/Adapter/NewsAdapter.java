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
import com.reynaldiwijaya.smartrtadmin.Model.Informasi.NewsItem;
import com.reynaldiwijaya.smartrtadmin.R;
import com.reynaldiwijaya.smartrtadmin.Utills.Constants;
import com.reynaldiwijaya.smartrtadmin.View.DetailNewsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private Context context;
    private List<NewsItem> newsItemList;

    public NewsAdapter(Context context, List<NewsItem> newsItemList) {
        this.context = context;
        this.newsItemList = newsItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final NewsItem newsItem = newsItemList.get(i);

        viewHolder.tvPenulis.setText(newsItem.getNamaLengkap());
        viewHolder.tvDate.setText(newsItem.getTglNulis());
        viewHolder.tvJudul.setText(newsItem.getJudul());
        viewHolder.tvDeskripsi.setText(newsItem.getContent());

        RequestOptions requestOptions = new RequestOptions().error(R.drawable.ic_broken_image_black_24dp).placeholder(R.drawable.ic_broken_image_black_24dp);
        Glide.with(context)
                .load(Constants.IMAGE_INFORMASI_URL + newsItem.getFotoInformasi())
                .apply(requestOptions)
                .into(viewHolder.imgBerita);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.FOTO_INFORMASI, newsItem.getFotoInformasi());
                bundle.putString(Constants.CONTENT, newsItem.getContent());
                bundle.putString(Constants.JUDUL, newsItem.getJudul());
                bundle.putString(Constants.ID, newsItem.getId_informasi());
                bundle.putString(Constants.DATE, newsItem.getTglNulis());
                bundle.putString(Constants.NAMA, newsItem.getNamaLengkap());
                bundle.putString(Constants.FOTO, newsItem.getFoto());
                bundle.putString(Constants.NO_TLP, newsItem.getNoTlp());

                context.startActivity(new Intent(context, DetailNewsActivity.class).putExtras(bundle));
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_berita)
        ImageView imgBerita;
        @BindView(R.id.tv_judul)
        TextView tvJudul;
        @BindView(R.id.tv_penulis)
        TextView tvPenulis;
        @BindView(R.id.tv_titik)
        TextView tvTitik;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_deskripsi)
        TextView tvDeskripsi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

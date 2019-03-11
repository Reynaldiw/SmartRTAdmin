package com.reynaldiwijaya.smartrtadmin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.reynaldiwijaya.smartrtadmin.Model.Report.LaporanItem;
import com.reynaldiwijaya.smartrtadmin.R;
import com.reynaldiwijaya.smartrtadmin.Ui.DetailReportActivity;
import com.reynaldiwijaya.smartrtadmin.Utills.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder> {
    private Context context;
    private List<LaporanItem> laporanItemList;

    public ReportAdapter(Context context, List<LaporanItem> laporanItemList) {
        this.context = context;
        this.laporanItemList = laporanItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_report, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final LaporanItem laporanItem = laporanItemList.get(i);

        viewHolder.tvJudul.setText(laporanItem.getJudul());
        viewHolder.tvContent.setText(laporanItem.getLaporan());
        viewHolder.tvPelapor.setText(laporanItem.getNamaLengkap());
        viewHolder.tvTanggal.setText(laporanItem.getTglLapor());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.JUDUL, laporanItem.getJudul());
                bundle.putString(Constants.CONTENT, laporanItem.getLaporan());
                bundle.putString(Constants.DATE, laporanItem.getTglLapor());
                bundle.putString(Constants.FOTO, laporanItem.getFoto());
                bundle.putString(Constants.NAMA, laporanItem.getNamaLengkap());
                bundle.putString(Constants.NO_TLP, laporanItem.getNoTlp());
                bundle.putString(Constants.ID, laporanItem.getIdKritik());

                context.startActivity(new Intent(context, DetailReportActivity.class).putExtras(bundle));
            }
        });
    }

    @Override
    public int getItemCount() {
        return laporanItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_judul)
        TextView tvJudul;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_pelapor)
        TextView tvPelapor;
        @BindView(R.id.tv_tanggal)
        TextView tvTanggal;
        @BindView(R.id.cv_report)
        CardView cvReport;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

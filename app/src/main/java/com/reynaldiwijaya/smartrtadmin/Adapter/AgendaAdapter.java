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
import com.reynaldiwijaya.smartrtadmin.Model.Agenda.AgendaItem;
import com.reynaldiwijaya.smartrtadmin.R;
import com.reynaldiwijaya.smartrtadmin.Utills.Constants;
import com.reynaldiwijaya.smartrtadmin.View.DetailAgendaActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.ViewHolder> {

    private final Context context;
    private final List<AgendaItem> agendaItemList;

    public AgendaAdapter(Context context, List<AgendaItem> agendaItemList) {
        this.context = context;
        this.agendaItemList = agendaItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_agenda, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final AgendaItem agendaItem = agendaItemList.get(i);

        viewHolder.tvContent.setText(agendaItem.getContent());
        viewHolder.tvJudul.setText(agendaItem.getJudul());
        viewHolder.tvTanggal.setText(agendaItem.getTanggal());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                bundle.putString(Constants.ID, agendaItem.getIdJudul());
                bundle.putString(Constants.JUDUL, agendaItem.getJudul());
                bundle.putString(Constants.CONTENT, agendaItem.getContent());
                bundle.putString(Constants.DATE, agendaItem.getTanggal());
                bundle.putString(Constants.PLACE, agendaItem.getTempat());

                context.startActivity(new Intent(context, DetailAgendaActivity.class).putExtras(bundle));
            }
        });

    }

    @Override
    public int getItemCount() {
        return agendaItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_judul)
        TextView tvJudul;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_tanggal)
        TextView tvTanggal;
        @BindView(R.id.cv_agenda)
        CardView cvAgenda;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

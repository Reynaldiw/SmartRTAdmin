package com.reynaldiwijaya.smartrtadmin.View;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.reynaldiwijaya.smartrtadmin.Adapter.AgendaAdapter;
import com.reynaldiwijaya.smartrtadmin.Model.Agenda.AgendaItem;
import com.reynaldiwijaya.smartrtadmin.Presenter.Agenda.AgendaContract;
import com.reynaldiwijaya.smartrtadmin.Presenter.Agenda.AgendaPresenter;
import com.reynaldiwijaya.smartrtadmin.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class AgendaConfirmationActivity extends AppCompatActivity implements AgendaContract.View {

    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    @BindView(R.id.tv_pesan)
    TextView tvPesan;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    private AgendaPresenter agendaPresenter = new AgendaPresenter(this);
    private List<AgendaItem> agendaItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_confirmation);
        ButterKnife.bind(this);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                agendaItems.clear();
                agendaPresenter.getDataList();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        agendaItems.clear();
        agendaPresenter.getDataList();
    }

    @Override
    public void showProgress() {
        swipeRefresh.setRefreshing(true);

    }

    @Override
    public void hideProgress() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showDataList(List<AgendaItem> agendaItemList) {
        agendaItems.addAll(agendaItemList);
        initAdapter();
    }

    private void initAdapter() {
        rvMain.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvMain.setAdapter(new AgendaAdapter(this, agendaItems));
    }

    @Override
    public void showFailureMessage(String msg) {
        Toasty.error(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPesan() {
        initAdapter();
        tvPesan.setVisibility(View.VISIBLE);
    }
}

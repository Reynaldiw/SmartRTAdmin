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

import com.reynaldiwijaya.smartrtadmin.Adapter.ReportAdapter;
import com.reynaldiwijaya.smartrtadmin.Model.Report.LaporanItem;
import com.reynaldiwijaya.smartrtadmin.Presenter.Report.ReportContract;
import com.reynaldiwijaya.smartrtadmin.Presenter.Report.ReportPresenter;
import com.reynaldiwijaya.smartrtadmin.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class ReportActivity extends AppCompatActivity implements ReportContract.View {

    @BindView(R.id.rv_report)
    RecyclerView rvReport;
    @BindView(R.id.tv_pesan)
    TextView tvPesan;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    private ReportPresenter reportPresenter = new ReportPresenter(this);
    private List<LaporanItem> laporanItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                laporanItems.clear();
                reportPresenter.getLaporan();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        laporanItems.clear();
        reportPresenter.getLaporan();
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
    public void showDataList(List<LaporanItem> laporanItemList) {
        laporanItems.addAll(laporanItemList);
        initAdapter();
    }

    private void initAdapter() {
        rvReport.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvReport.setAdapter(new ReportAdapter(this, laporanItems));
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

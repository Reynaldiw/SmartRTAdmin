package com.reynaldiwijaya.smartrtadmin.View;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.reynaldiwijaya.smartrtadmin.Adapter.StoreAdapter;
import com.reynaldiwijaya.smartrtadmin.Model.Store.StoreItem;
import com.reynaldiwijaya.smartrtadmin.Presenter.Store.StoreContract;
import com.reynaldiwijaya.smartrtadmin.Presenter.Store.StorePresenter;
import com.reynaldiwijaya.smartrtadmin.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class StoreConfirmationActivity extends AppCompatActivity implements StoreContract.View {

    @BindView(R.id.rv_store)
    RecyclerView rvStore;
    @BindView(R.id.tv_pesan)
    TextView tvPesan;

    private ProgressDialog progressDialog;
    private StorePresenter storePresenter = new StorePresenter(this);
    private List<StoreItem> storeItems = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setColorMode(getResources().getColor(R.color.colorPrimaryDark));

        setContentView(R.layout.activity_store_confirmation);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        storeItems.clear();
        storePresenter.getDataStore();
    }

    @Override
    public void showProgress() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading ...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }

    }

    @Override
    public void showDataList(List<StoreItem> storeItemList) {
        storeItems.addAll(storeItemList);
        initAdapter();
    }

    private void initAdapter() {
        rvStore.setLayoutManager(new LinearLayoutManager(this));
        rvStore.setAdapter(new StoreAdapter(this, storeItems));
    }

    @Override
    public void showFailureMessage(String msg) {
        Toasty.error(this, msg, Toasty.LENGTH_SHORT).show();
    }

    @Override
    public void showPesan() {
        initAdapter();
        tvPesan.setVisibility(View.VISIBLE);
    }
}

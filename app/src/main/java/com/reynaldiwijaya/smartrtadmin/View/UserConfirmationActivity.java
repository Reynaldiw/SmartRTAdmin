package com.reynaldiwijaya.smartrtadmin.View;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.reynaldiwijaya.smartrtadmin.Adapter.UserAdapter;
import com.reynaldiwijaya.smartrtadmin.Model.User.UserItem;
import com.reynaldiwijaya.smartrtadmin.Presenter.User.UserContract;
import com.reynaldiwijaya.smartrtadmin.Presenter.User.UserPresenter;
import com.reynaldiwijaya.smartrtadmin.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class UserConfirmationActivity extends AppCompatActivity implements UserContract.View {

    @BindView(R.id.rv_user)
    RecyclerView rvUser;
    @BindView(R.id.tv_pesan)
    TextView tvPesan;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    private UserPresenter userPresenter = new UserPresenter(this);
    private List<UserItem> userItems = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_confirmation);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setColorMode(getResources().getColor(R.color.colorPrimaryDark));

        ButterKnife.bind(this);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                userItems.clear();
                userPresenter.getDataUser();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        userItems.clear();
        userPresenter.getDataUser();
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
    public void showDataUser(List<UserItem> userItemList) {
        userItems.addAll(userItemList);
        initAdapter();
    }

    private void initAdapter() {
        rvUser.setLayoutManager(new LinearLayoutManager(this));
        rvUser.setAdapter(new UserAdapter(this, userItems));
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

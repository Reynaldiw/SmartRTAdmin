package com.reynaldiwijaya.smartrtadmin.View;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.reynaldiwijaya.smartrtadmin.Presenter.User.UserDetailContract;
import com.reynaldiwijaya.smartrtadmin.Presenter.User.UserDetailPresenter;
import com.reynaldiwijaya.smartrtadmin.R;
import com.reynaldiwijaya.smartrtadmin.Utills.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class DetailUserActivity extends AppCompatActivity implements UserDetailContract.View {


    @BindView(R.id.img_user)
    CircleImageView imgUser;
    @BindView(R.id.tv_nama)
    TextView tvNama;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.tv_jenkel)
    TextView tvJenkel;
    @BindView(R.id.tv_profesi)
    TextView tvProfesi;
    @BindView(R.id.tv_noKtp)
    TextView tvNoKtp;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.tv_birth)
    TextView tvBirth;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_call)
    TextView tvCall;
    @BindView(R.id.btn_confirmation)
    Button btnConfirmation;
    @BindView(R.id.line1)
    LinearLayout line1;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    private Bundle bundle;
    private String id;
    private UserDetailPresenter userDetailPresenter = new UserDetailPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
        ButterKnife.bind(this);

        bundle = getIntent().getExtras();

        if (bundle != null) {
            id = bundle.getString(Constants.ID);
            setUpList();
        }

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setUpList();
            }
        });
    }

    private void setUpList() {
        tvNama.setText(bundle.getString(Constants.NAMA));
        tvNoKtp.setText(bundle.getString(Constants.NO_KTP));
        tvHome.setText(bundle.getString(Constants.ALAMAT));
        tvStatus.setText(bundle.getString(Constants.STATUS));
        tvBirth.setText(bundle.getString(Constants.DATE));
        tvJenkel.setText(bundle.getString(Constants.JENKEL));
        tvProfesi.setText(bundle.getString(Constants.PROFESI));
        tvCall.setText(bundle.getString(Constants.NO_TLP));
        tvEmail.setText(bundle.getString(Constants.EMAIL));
        tvLevel.setText(bundle.getString(Constants.LEVEL));

        RequestOptions requestOptions = new RequestOptions().error(R.drawable.avatar).placeholder(R.drawable.avatar);
        Glide.with(this)
                .load(Constants.IMAGE_USER_URL + bundle.getString(Constants.FOTO))
                .apply(requestOptions)
                .into(imgUser);
        swipeRefresh.setRefreshing(false);
    }

    @OnClick({R.id.btn_confirmation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_confirmation:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure want to confirmation ?");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userDetailPresenter.updateUser(id);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
        }
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
    public void showSuccesMessage(String msg) {
        Toasty.success(this, msg, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showFailureMessage(String msg) {
        Toasty.error(this, msg, Toast.LENGTH_SHORT).show();

    }
}

package com.reynaldiwijaya.smartrtadmin.Ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.reynaldiwijaya.smartrtadmin.R;
import com.reynaldiwijaya.smartrtadmin.Utills.Constants;
import com.reynaldiwijaya.smartrtadmin.Utills.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

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
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    private SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        sm = new SessionManager(this);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setData();
            }
        });

        setData();
    }

    private void setData() {
        swipeRefresh.setRefreshing(true);

        RequestOptions requestOptions = new RequestOptions().error(R.drawable.avatar).placeholder(R.drawable.avatar);
        Glide.with(this)
                .load(Constants.IMAGE_USER_URL + sm.getImageUser())
                .apply(requestOptions)
                .into(imgUser);

        tvNama.setText(sm.getNama());
        tvLevel.setText(sm.getLevel());
        tvProfesi.setText(sm.getProfesi());
        tvBirth.setText(sm.getTglLahir());
        tvEmail.setText(sm.getEmailUser());
        tvCall.setText(sm.getNoTlp());
        tvHome.setText(sm.getAlamat());
        tvJenkel.setText(sm.getJenkel());
        tvStatus.setText(sm.getStatus());

        swipeRefresh.setRefreshing(false);
    }
}

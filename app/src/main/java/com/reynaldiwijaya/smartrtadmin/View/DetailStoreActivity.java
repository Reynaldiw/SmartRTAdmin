package com.reynaldiwijaya.smartrtadmin.View;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.reynaldiwijaya.smartrtadmin.Presenter.Store.StoreDetailContract;
import com.reynaldiwijaya.smartrtadmin.Presenter.Store.StoreDetailPresenter;
import com.reynaldiwijaya.smartrtadmin.R;
import com.reynaldiwijaya.smartrtadmin.Utills.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class DetailStoreActivity extends AppCompatActivity implements StoreDetailContract.View {


    @BindView(R.id.ivGambarBerita)
    ImageView ivGambarBerita;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing)
    CollapsingToolbarLayout collapsing;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.tv_deskripsi)
    TextView tvDeskripsi;
    @BindView(R.id.tv_alamat)
    TextView tvAlamat;
    @BindView(R.id.tv_noTlp_toko)
    TextView tvNoTlpToko;
    @BindView(R.id.img_profile)
    CircleImageView imgProfile;
    @BindView(R.id.tv_nama)
    TextView tvNama;
    @BindView(R.id.tv_noTlp)
    TextView tvNoTlp;
    @BindView(R.id.btn_konfirmasi)
    Button btnKonfirmasi;

    private Bundle bundle;
    private StoreDetailPresenter storeDetailPresenter = new StoreDetailPresenter(this);
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_store);
        ButterKnife.bind(this);

        bundle = getIntent().getExtras();

        if (bundle != null) {
            showData();
        }

    }

    private void showData() {
        tvDeskripsi.setText(bundle.getString(Constants.DESKRIPSI));
        tvAlamat.setText(bundle.getString(Constants.ALAMAT));
        tvNama.setText(bundle.getString(Constants.NAMA));
        tvNoTlpToko.setText(bundle.getString(Constants.NO_TLP_TOKO));
        tvNoTlp.setText(bundle.getString(Constants.NO_TLP));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout.setTitle(bundle.getString(Constants.NAMA_TOKO));

        collapsingToolbarLayout.setCollapsedTitleTextColor(
                ContextCompat.getColor(this, android.R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(
                ContextCompat.getColor(this, android.R.color.white));

        RequestOptions requestOptions = new RequestOptions().error(R.drawable.ic_broken_image_black_24dp);
        Glide.with(this)
                .load(Constants.IMAGE_STORE_URL + bundle.getString(Constants.FOTO_TOKO))
                .apply(requestOptions)
                .into(ivGambarBerita);

        RequestOptions options = new RequestOptions().error(R.drawable.avatar);
        Glide.with(this)
                .load(Constants.IMAGE_USER_URL + bundle.getString(Constants.FOTO))
                .apply(options)
                .into(imgProfile);

    }

    @OnClick({ R.id.btn_konfirmasi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_konfirmasi:
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
                        storeDetailPresenter.updateStore(bundle.getString(Constants.ID));
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
        }
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
    public void showSuccesMessage(String msg) {
        Toasty.success(this, msg, Toasty.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showFailureMessage(String msg) {
        Toasty.error(this, msg, Toasty.LENGTH_SHORT).show();
    }
}

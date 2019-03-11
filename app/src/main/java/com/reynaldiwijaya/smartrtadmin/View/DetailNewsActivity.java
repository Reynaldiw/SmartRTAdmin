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
import com.reynaldiwijaya.smartrtadmin.Presenter.News.DetailNewsContract;
import com.reynaldiwijaya.smartrtadmin.Presenter.News.DetailNewsPresenter;
import com.reynaldiwijaya.smartrtadmin.R;
import com.reynaldiwijaya.smartrtadmin.Utills.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class DetailNewsActivity extends AppCompatActivity implements DetailNewsContract.View {

    @BindView(R.id.ivGambarBerita)
    ImageView ivGambarBerita;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing)
    CollapsingToolbarLayout collapsing;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.img_profile)
    CircleImageView imgProfile;
    @BindView(R.id.tv_nama)
    TextView tvNama;
    @BindView(R.id.tv_noTlp)
    TextView tvNoTlp;
    @BindView(R.id.img_call)
    ImageButton imgCall;
    @BindView(R.id.btn_konfirmasi)
    Button btnKonfirmasi;

    private Bundle bundle;
    private ProgressDialog progressDialog;
    private DetailNewsPresenter detailNewsPresenter = new DetailNewsPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        ButterKnife.bind(this);

        bundle = getIntent().getExtras();

        if (bundle != null) {
            showData();
        }

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout.setTitle(bundle.getString(Constants.JUDUL));

        collapsingToolbarLayout.setCollapsedTitleTextColor(
                ContextCompat.getColor(this, android.R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(
                ContextCompat.getColor(this, android.R.color.white));
    }

    private void showData() {
        tvContent.setText(bundle.getString(Constants.CONTENT));
        tvDate.setText(bundle.getString(Constants.DATE));
        tvNama.setText(bundle.getString(Constants.NAMA));
        tvNoTlp.setText(bundle.getString(Constants.NO_TLP));

        RequestOptions requestOptions = new RequestOptions().error(R.drawable.ic_broken_image_black_24dp).placeholder(R.drawable.ic_broken_image_black_24dp);
        Glide.with(this)
                .load(Constants.IMAGE_INFORMASI_URL + bundle.getString(Constants.FOTO_INFORMASI))
                .apply(requestOptions)
                .into(ivGambarBerita);

        RequestOptions options = new RequestOptions().error(R.drawable.avatar).placeholder(R.drawable.avatar);
        Glide.with(this)
                .load(Constants.IMAGE_USER_URL + bundle.getString(Constants.FOTO))
                .apply(options)
                .into(imgProfile);

    }



    @OnClick({R.id.img_call, R.id.btn_konfirmasi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_call:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: " + bundle.getString(Constants.NO_TLP)));
                break;
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
                        detailNewsPresenter.getDetailNews(bundle.getString(Constants.ID));
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

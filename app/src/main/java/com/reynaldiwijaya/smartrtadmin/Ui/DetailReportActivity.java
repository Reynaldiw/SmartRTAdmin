package com.reynaldiwijaya.smartrtadmin.Ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.reynaldiwijaya.smartrtadmin.Api.ApiClient;
import com.reynaldiwijaya.smartrtadmin.Api.ApiInterface;
import com.reynaldiwijaya.smartrtadmin.Model.Report.ResponseReply;
import com.reynaldiwijaya.smartrtadmin.R;
import com.reynaldiwijaya.smartrtadmin.Utills.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailReportActivity extends AppCompatActivity {

    @BindView(R.id.tv_judul)
    TextView tvJudul;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.img_profile)
    CircleImageView imgProfile;
    @BindView(R.id.tv_nama)
    TextView tvNama;
    @BindView(R.id.tv_noTlp)
    TextView tvNoTlp;
    @BindView(R.id.img_call)
    ImageButton imgCall;
    @BindView(R.id.tv_tanggal)
    TextView tvTanggal;
    @BindView(R.id.btn_reply)
    Button btnReply;

    private Bundle bundle;
    private Dialog dialog;
    TextInputEditText edtBalasan;
    Button button_reply, button_cancel;
    private ApiInterface apiInterface;
    private String reply;
    private ProgressDialog progressDialog;
    private ResponseReply responseReply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_report);
        ButterKnife.bind(this);

        bundle = getIntent().getExtras();

        if (bundle != null) {
            showData();
        }

    }

    private void showData() {
        tvJudul.setText(bundle.getString(Constants.JUDUL));
        tvContent.setText(bundle.getString(Constants.CONTENT));
        tvNama.setText(bundle.getString(Constants.NAMA));
        tvNoTlp.setText(bundle.getString(Constants.NO_TLP));
        tvTanggal.setText(bundle.getString(Constants.DATE));

        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_broken_image_black_24dp).error(R.drawable.ic_broken_image_black_24dp);
        Glide.with(this)
                .load(bundle.getString(Constants.FOTO))
                .apply(requestOptions)
                .into(imgProfile);
    }

    @OnClick({R.id.img_call, R.id.btn_reply})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.img_call:
            Intent toPhone = new Intent(Intent.ACTION_DIAL);
            toPhone.setData(Uri.parse("tel:" + bundle.getString(Constants.NO_TLP)));
            startActivity(toPhone);
            break;

            case R.id.btn_reply:

                dialog = new Dialog(this);
                dialog.setContentView(R.layout.reply_dialog);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(false);

                edtBalasan = dialog.findViewById(R.id.edt_reply);
                button_reply = dialog.findViewById(R.id.btn_reply);
                button_cancel = dialog.findViewById(R.id.btn_cancel);

                button_reply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(DetailReportActivity.this);
                        builder.setMessage("Send this reply ?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                reply = edtBalasan.getText().toString();
                                if (TextUtils.isEmpty(reply)) {
                                    edtBalasan.setError(getString(R.string.error_message));
                                    edtBalasan.requestFocus();
                                }

                                showProgressDialog("Try to send reply");
                                apiInterface = ApiClient.getClient().create(ApiInterface.class);
                                Call<ResponseReply> call = apiInterface.reply(bundle.getString(Constants.ID), reply);
                                call.enqueue(new Callback<ResponseReply>() {
                                    @Override
                                    public void onResponse(Call<ResponseReply> call, Response<ResponseReply> response) {
                                        hideProgressDialog();
                                        if (response.body() != null) {

                                            if (response.body().getResult().equals("1")) {
                                                responseReply = response.body();

                                                Toasty.success(DetailReportActivity.this, responseReply.getMessage(),Toasty.LENGTH_SHORT).show();
                                                finish();
                                            } else {
                                                Toasty.error(DetailReportActivity.this, responseReply.getMessage(), Toasty.LENGTH_SHORT).show();
                                            }

                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseReply> call, Throwable t) {
                                        hideProgressDialog();
                                        Toasty.error(DetailReportActivity.this, t.getMessage(), Toasty.LENGTH_SHORT).show();

                                    }
                                });
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                });

                button_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
                break;
        }
    }

    private void hideProgressDialog() {
        progressDialog.dismiss();
    }

    private void showProgressDialog(String message) {
        progressDialog = new ProgressDialog(DetailReportActivity.this);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

}

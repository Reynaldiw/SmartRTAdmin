package com.reynaldiwijaya.smartrtadmin.View;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.reynaldiwijaya.smartrtadmin.Presenter.Agenda.DetailAgendaContarct;
import com.reynaldiwijaya.smartrtadmin.Presenter.Agenda.DetailAgendaPresenter;
import com.reynaldiwijaya.smartrtadmin.R;
import com.reynaldiwijaya.smartrtadmin.Utills.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class DetailAgendaActivity extends AppCompatActivity implements DetailAgendaContarct.View {

    @BindView(R.id.tv_judul)
    TextView tvJudul;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_tempat)
    TextView tvTempat;
    @BindView(R.id.tv_tanggal)
    TextView tvTanggal;
    @BindView(R.id.btn_konfirmasi)
    Button btnKonfirmasi;

    private Bundle bundle;
    private DetailAgendaPresenter detailAgendaPresenter = new DetailAgendaPresenter(this);
    private ProgressDialog progressDialog;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_agenda);
        ButterKnife.bind(this);

        bundle = getIntent().getExtras();

        if (bundle != null) {
            id = bundle.getString(Constants.ID);
            setUpList();
        }
    }

    private void setUpList() {
        tvContent.setText(bundle.getString(Constants.CONTENT));
        tvJudul.setText(bundle.getString(Constants.JUDUL));
        tvTanggal.setText(bundle.getString(Constants.DATE));
        tvTempat.setText(bundle.getString(Constants.PLACE));
    }

    @OnClick(R.id.btn_konfirmasi)
    public void onViewClicked() {
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
                detailAgendaPresenter.doConfirmation(id);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
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

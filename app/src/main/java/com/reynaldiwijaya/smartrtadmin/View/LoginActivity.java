package com.reynaldiwijaya.smartrtadmin.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.reynaldiwijaya.smartrtadmin.Presenter.Login.LoginContract;
import com.reynaldiwijaya.smartrtadmin.Presenter.Login.LoginPresenter;
import com.reynaldiwijaya.smartrtadmin.R;
import com.reynaldiwijaya.smartrtadmin.Ui.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    @BindView(R.id.edt_username)
    TextInputEditText edtUsername;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    private LoginPresenter loginPresenter = new LoginPresenter(this, this);
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_login, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                loginPresenter.doLogin(edtUsername.getText().toString(), edtPassword.getText().toString());
                break;
            case R.id.tv_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Try to Login ...");
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
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void showFailureMessage(String msg) {
        Toasty.error(this, msg, Toast.LENGTH_SHORT).show();
    }

}

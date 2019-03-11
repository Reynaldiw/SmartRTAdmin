package com.reynaldiwijaya.smartrtadmin.Presenter.Login;

import android.content.Context;

import com.reynaldiwijaya.smartrtadmin.Api.ApiClient;
import com.reynaldiwijaya.smartrtadmin.Api.ApiInterface;
import com.reynaldiwijaya.smartrtadmin.Model.Login.ResponseLogin;
import com.reynaldiwijaya.smartrtadmin.Model.Login.User;
import com.reynaldiwijaya.smartrtadmin.Presenter.Login.LoginContract;
import com.reynaldiwijaya.smartrtadmin.Utills.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    private Context context;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private SessionManager sm ;

    public LoginPresenter(LoginContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void doLogin(String username, String password) {
        sm = new SessionManager(context);
        if (username == null || username.isEmpty()) {
            view.showFailureMessage("Field tidak boleh kosong");
            return;
        }
        if (password == null || password.isEmpty()) {
            view.showFailureMessage("Field tidak boleh kosong");
        }

        view.showProgress();
        Call<ResponseLogin> call = apiInterface.login(username, password);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                view.hideProgress();
                if (response.body() != null) {
                    ResponseLogin responseLogin = response.body();

                    if (responseLogin.getResult().equals("1")) {
                        view.showSuccesMessage(responseLogin.getMsg());

                        User user = responseLogin.getUser();
                        String id = user.getIdUser();
                        sm.storeLogin(user.getUsername());
                        sm.setIdUser(id);
                        sm.setEmailUser(user.getEmail());
                        sm.setImageUser(user.getFoto());
                        sm.setNama(user.getNamaLengkap());
                        sm.setAlamat(user.getEmail());
                        sm.setJenkel(user.getJenkel());
                        sm.setLevel(user.getLevel());
                        sm.setNoKtp(user.getNoKtp());
                        sm.setNoTlp(user.getNoTlp());
                        sm.setProfesi(user.getProfesi());
                        sm.setStatus(user.getStatus());
                        sm.setTglLahir(user.getTglLahir());

                    } else {
                        view.showFailureMessage(responseLogin.getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                view.hideProgress();
                view.showFailureMessage(t.getMessage());

            }
        });
    }
}

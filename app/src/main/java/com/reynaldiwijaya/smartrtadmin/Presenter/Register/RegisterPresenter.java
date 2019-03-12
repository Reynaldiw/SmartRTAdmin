package com.reynaldiwijaya.smartrtadmin.Presenter.Register;

import android.content.Context;

import com.reynaldiwijaya.smartrtadmin.Api.ApiClient;
import com.reynaldiwijaya.smartrtadmin.Api.ApiInterface;
import com.reynaldiwijaya.smartrtadmin.Model.Register.ResponseRegister;
import com.reynaldiwijaya.smartrtadmin.Utills.Constants;

import net.gotev.uploadservice.MultipartUploadRequest;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter implements RegisterContract.Presenter {
    private RegisterContract.View view;
    private Context context;
    private ApiInterface apiInterface;

    public RegisterPresenter(RegisterContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void doRegister(RequestBody nama, RequestBody no_ktp, RequestBody alamat, RequestBody status, RequestBody tgl_lahir, RequestBody jenkel, RequestBody profesi, RequestBody no_tlp, RequestBody email, RequestBody username, RequestBody password, MultipartBody.Part path, RequestBody konfirmasi, RequestBody level) {
        view.showProgress();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseRegister> call = apiInterface.registerUser(nama, no_ktp, alamat, status, tgl_lahir, jenkel, profesi, no_tlp, email, username, password, konfirmasi, level, path);
        call.enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                view.hideProgress();

                ResponseRegister responseRegister = response.body();

                if (responseRegister.getResult().equals("-1")) {
                    view.showFailureMessage("Username telah terdaftar");
                }
                if (responseRegister.getResult().equals("0")) {
                    view.showFailureMessage("Gagal Register");
                }
                if (responseRegister.getResult().equals("1")) {
                    view.showSuccesMessage("Berhasil Register");
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                view.hideProgress();

                view.showFailureMessage(t.getMessage());
            }
        });

    }
}

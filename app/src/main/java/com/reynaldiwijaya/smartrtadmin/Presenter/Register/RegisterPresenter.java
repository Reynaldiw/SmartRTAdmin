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

public class RegisterPresenter implements RegisterContract.Presenter {
    private RegisterContract.View view;
    private Context context;

    public RegisterPresenter(RegisterContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void doRegister(String nama, String no_ktp, String alamat, String status, String tgl_lahir,
                           String jenkel, String profesi, String no_tlp, String email, String username,
                           String password, String path, String konfirmasi, String level) {

        view.showProgress();

        try {
            new MultipartUploadRequest(context, Constants.UPLOAD_REGISTER_URL)
                    .addFileToUpload(path, "foto")
                    .addParameter("nama_lengkap", nama)
                    .addParameter("no_ktp", no_ktp)
                    .addParameter("alamat", alamat)
                    .addParameter("status", status)
                    .addParameter("tgl_lahir", tgl_lahir)
                    .addParameter("jenkel", jenkel)
                    .addParameter("profesi", profesi)
                    .addParameter("no_tlp", no_tlp)
                    .addParameter("email", email)
                    .addParameter("username", username)
                    .addParameter("password", password)
                    .addParameter("konfirmasi", konfirmasi)
                    .addParameter("level", level)
                    .setMaxRetries(2)
                    .startUpload();
            view.hideProgress();
            view.showSuccesMessage("Succes to Register");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            view.hideProgress();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            view.hideProgress();
        }
    }
}

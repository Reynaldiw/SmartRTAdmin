package com.reynaldiwijaya.smartrtadmin.Presenter.Register;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface RegisterContract {
    interface View {
        void showProgress();
        void hideProgress();
        void showSuccesMessage(String msg);
        void showFailureMessage(String msg);
    }

    interface Presenter {
        void doRegister(String nama, String no_ktp, String alamat, String status,
                        String tgl_lahir, String jenkel, String profesi, String no_tlp,
                        String email, String username, String password, String path,
                        String konfirmasi, String level);


    }
}

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
        void doRegister(RequestBody nama, RequestBody no_ktp, RequestBody alamat, RequestBody status,
                        RequestBody tgl_lahir, RequestBody jenkel, RequestBody profesi, RequestBody no_tlp,
                        RequestBody email, RequestBody username, RequestBody password, MultipartBody.Part path,
                        RequestBody konfirmasi, RequestBody level);


    }
}

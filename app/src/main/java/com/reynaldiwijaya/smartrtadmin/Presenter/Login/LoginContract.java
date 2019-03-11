package com.reynaldiwijaya.smartrtadmin.Presenter.Login;

public interface LoginContract {
    interface View {
        void showProgress();
        void hideProgress();
        void showSuccesMessage(String msg);
        void showFailureMessage(String msg);
    }

    interface Presenter {
        void doLogin(String username, String password);
    }
}

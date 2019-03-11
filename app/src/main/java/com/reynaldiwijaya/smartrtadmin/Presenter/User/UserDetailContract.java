package com.reynaldiwijaya.smartrtadmin.Presenter.User;

public interface UserDetailContract {
    interface View {
        void showProgress();
        void hideProgress();
        void showSuccesMessage(String msg);
        void showFailureMessage(String msg);
    }

    interface Presenter {
        void updateUser(String id);
    }
}

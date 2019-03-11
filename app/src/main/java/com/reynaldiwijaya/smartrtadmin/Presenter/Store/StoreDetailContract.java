package com.reynaldiwijaya.smartrtadmin.Presenter.Store;

public interface StoreDetailContract {
    interface View {
        void showProgress();
        void hideProgress();
        void showSuccesMessage(String msg);
        void showFailureMessage(String msg);
    }

    interface Presenter {
        void updateStore(String id);
    }
}

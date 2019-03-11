package com.reynaldiwijaya.smartrtadmin.Presenter.News;

public interface DetailNewsContract {
    interface View {
        void showProgress();
        void hideProgress();
        void showSuccesMessage(String msg);
        void showFailureMessage(String msg);
    }

    interface Presenter {
        void getDetailNews(String id);
    }
}

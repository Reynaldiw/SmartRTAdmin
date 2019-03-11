package com.reynaldiwijaya.smartrtadmin.Presenter.News;

import com.reynaldiwijaya.smartrtadmin.Model.Informasi.NewsItem;

import java.util.List;

public interface NewsContract {
    interface View {
        void showProgress();
        void hideProgress();
        void showDataList(List<NewsItem> newsItemList);
        void showFailureMessage(String msg);
        void showPesan();
    }

    interface Presenter {
        void getNews();
    }
}

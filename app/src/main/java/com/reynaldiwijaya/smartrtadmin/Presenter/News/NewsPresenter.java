package com.reynaldiwijaya.smartrtadmin.Presenter.News;

import android.util.Log;

import com.reynaldiwijaya.smartrtadmin.Api.ApiClient;
import com.reynaldiwijaya.smartrtadmin.Api.ApiInterface;
import com.reynaldiwijaya.smartrtadmin.Model.Informasi.ResponseNews;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsPresenter implements NewsContract.Presenter {
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private NewsContract.View view;

    public NewsPresenter(NewsContract.View view) {
        this.view = view;
    }

    @Override
    public void getNews() {

        view.showProgress();

        Call<ResponseNews> call = apiInterface.getNews();
        call.enqueue(new Callback<ResponseNews>() {
            @Override
            public void onResponse(Call<ResponseNews> call, Response<ResponseNews> response) {
                view.hideProgress();
                if (response.body() != null) {
                    ResponseNews responseNews = response.body();

                    if (responseNews.getNews() != null) {
                        Log.i("News", "MASUK");
                        view.showDataList(responseNews.getNews());
                    } else {
                        view.showPesan();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseNews> call, Throwable t) {
                view.hideProgress();
                view.showFailureMessage(t.getMessage());

            }
        });

    }
}

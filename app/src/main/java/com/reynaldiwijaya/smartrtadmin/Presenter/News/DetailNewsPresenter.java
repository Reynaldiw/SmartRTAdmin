package com.reynaldiwijaya.smartrtadmin.Presenter.News;

import com.reynaldiwijaya.smartrtadmin.Api.ApiClient;
import com.reynaldiwijaya.smartrtadmin.Api.ApiInterface;
import com.reynaldiwijaya.smartrtadmin.Model.Informasi.ResponseKonfirmasiNews;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailNewsPresenter implements DetailNewsContract.Presenter {

    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private DetailNewsContract.View view;

    public DetailNewsPresenter(DetailNewsContract.View view) {
        this.view = view;
    }

    @Override
    public void getDetailNews(String id) {
        view.showProgress();

        Call<ResponseKonfirmasiNews> call = apiInterface.updateNews(id);
        call.enqueue(new Callback<ResponseKonfirmasiNews>() {
            @Override
            public void onResponse(Call<ResponseKonfirmasiNews> call, Response<ResponseKonfirmasiNews> response) {
                view.hideProgress();

                if (response.body() != null) {
                    ResponseKonfirmasiNews responseKonfirmasiNews = response.body();

                    if (responseKonfirmasiNews.getResult().equals("sukses")) {
                        view.showSuccesMessage(responseKonfirmasiNews.getPesan());
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponseKonfirmasiNews> call, Throwable t) {
                view.hideProgress();
                view.showFailureMessage(t.getMessage());
            }
        });
    }
}

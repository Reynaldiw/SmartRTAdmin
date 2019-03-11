package com.reynaldiwijaya.smartrtadmin.Presenter.Store;

import com.reynaldiwijaya.smartrtadmin.Api.ApiClient;
import com.reynaldiwijaya.smartrtadmin.Api.ApiInterface;
import com.reynaldiwijaya.smartrtadmin.Model.Store.ResponseStore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StorePresenter implements StoreContract.Presenter {
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private StoreContract.View view;

    public StorePresenter(StoreContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataStore() {
        view.showProgress();

        Call<ResponseStore> call = apiInterface.getStore();
        call.enqueue(new Callback<ResponseStore>() {
            @Override
            public void onResponse(Call<ResponseStore> call, Response<ResponseStore> response) {
                view.hideProgress();

                if (response.body() != null) {
                    ResponseStore responseStore = response.body();

                        if (responseStore.getStore() != null) {
                            view.showDataList(responseStore.getStore());
                        }else {
                            view.showPesan();
                        }

                }
            }

            @Override
            public void onFailure(Call<ResponseStore> call, Throwable t) {
                view.hideProgress();
                view.showFailureMessage(t.getMessage());
            }
        });
    }
}

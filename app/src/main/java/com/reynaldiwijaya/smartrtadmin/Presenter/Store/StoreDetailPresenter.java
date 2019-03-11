package com.reynaldiwijaya.smartrtadmin.Presenter.Store;

import com.reynaldiwijaya.smartrtadmin.Api.ApiClient;
import com.reynaldiwijaya.smartrtadmin.Api.ApiInterface;
import com.reynaldiwijaya.smartrtadmin.Model.Store.ResponseUpdateStore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreDetailPresenter implements StoreDetailContract.Presenter {
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    private StoreDetailContract.View view;

    public StoreDetailPresenter(StoreDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void updateStore(String id) {
        view.showProgress();

        Call<ResponseUpdateStore> call = apiInterface.updateStore(id);
        call.enqueue(new Callback<ResponseUpdateStore>() {
            @Override
            public void onResponse(Call<ResponseUpdateStore> call, Response<ResponseUpdateStore> response) {
                view.hideProgress();

                if (response.body() != null) {
                    ResponseUpdateStore responseUpdateStore = response.body();

                    if (responseUpdateStore.getResult().equals("sukses")) {
                        view.showSuccesMessage(responseUpdateStore.getPesan());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateStore> call, Throwable t) {
                view.hideProgress();
                view.showFailureMessage(t.getMessage());
            }
        });

    }
}

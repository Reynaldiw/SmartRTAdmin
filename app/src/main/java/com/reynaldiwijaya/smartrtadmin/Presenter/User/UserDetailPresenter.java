package com.reynaldiwijaya.smartrtadmin.Presenter.User;

import com.reynaldiwijaya.smartrtadmin.Api.ApiClient;
import com.reynaldiwijaya.smartrtadmin.Api.ApiInterface;
import com.reynaldiwijaya.smartrtadmin.Model.User.ResponseDetailUser;
import com.reynaldiwijaya.smartrtadmin.Model.User.ResponseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailPresenter implements UserDetailContract.Presenter {
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private UserDetailContract.View view;

    public UserDetailPresenter(UserDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void updateUser(String id) {
        view.showProgress();

        Call<ResponseDetailUser> call = apiInterface.updateUser(id);
        call.enqueue(new Callback<ResponseDetailUser>() {
            @Override
            public void onResponse(Call<ResponseDetailUser> call, Response<ResponseDetailUser> response) {
                view.hideProgress();
                if (response.body() != null) {
                    ResponseDetailUser responseDetailUser = response.body();

                    if (responseDetailUser.getResult().equals("sukses")) {
                        view.showSuccesMessage(responseDetailUser.getPesan());
                    } else {
                        view.showFailureMessage(responseDetailUser.getPesan());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailUser> call, Throwable t) {
                view.hideProgress();
                view.showFailureMessage(t.getMessage());
            }
        });
    }
}

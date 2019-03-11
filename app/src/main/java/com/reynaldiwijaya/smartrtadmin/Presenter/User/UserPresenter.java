package com.reynaldiwijaya.smartrtadmin.Presenter.User;

import com.reynaldiwijaya.smartrtadmin.Api.ApiClient;
import com.reynaldiwijaya.smartrtadmin.Api.ApiInterface;
import com.reynaldiwijaya.smartrtadmin.Model.User.ResponseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPresenter implements UserContract.Presenter {

    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private final UserContract.View view;

    public UserPresenter(UserContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataUser() {
        view.showProgress();

        Call<ResponseUser> call = apiInterface.getUser();
        call.enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                view.hideProgress();
                if (response.body() != null) {
                    ResponseUser responseUser = response.body();

                    if (responseUser.getUser() != null) {
                        view.showDataUser(responseUser.getUser());
                    } else {
                        view.showPesan();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                view.hideProgress();
                view.showFailureMessage(t.getMessage());

            }
        });
    }
}

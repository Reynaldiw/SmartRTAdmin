package com.reynaldiwijaya.smartrtadmin.Presenter.Agenda;

import com.reynaldiwijaya.smartrtadmin.Api.ApiClient;
import com.reynaldiwijaya.smartrtadmin.Api.ApiInterface;
import com.reynaldiwijaya.smartrtadmin.Model.Agenda.ResponseUpdateAgenda;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailAgendaPresenter implements DetailAgendaContarct.Presenter {

    public DetailAgendaPresenter(DetailAgendaContarct.View view) {
        this.view = view;
    }

    private final DetailAgendaContarct.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    @Override
    public void doConfirmation(String id) {
        view.showProgress();

        Call<ResponseUpdateAgenda> call = apiInterface.updateAgenda(id);
        call.enqueue(new Callback<ResponseUpdateAgenda>() {
            @Override
            public void onResponse(Call<ResponseUpdateAgenda> call, Response<ResponseUpdateAgenda> response) {
                view.hideProgress();
                if (response.body() != null) {
                    ResponseUpdateAgenda responseUpdateAgenda = response.body();

                    if (responseUpdateAgenda.getPesan() != null) {
                        view.showSuccesMessage(responseUpdateAgenda.getPesan());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateAgenda> call, Throwable t) {
                view.hideProgress();
                view.showFailureMessage(t.getMessage());
            }
        });

    }
}

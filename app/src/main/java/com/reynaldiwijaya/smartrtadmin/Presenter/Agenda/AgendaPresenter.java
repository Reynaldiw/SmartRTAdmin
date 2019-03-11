package com.reynaldiwijaya.smartrtadmin.Presenter.Agenda;

import com.reynaldiwijaya.smartrtadmin.Api.ApiClient;
import com.reynaldiwijaya.smartrtadmin.Api.ApiInterface;
import com.reynaldiwijaya.smartrtadmin.Model.Agenda.AgendaItem;
import com.reynaldiwijaya.smartrtadmin.Model.Agenda.ResponseAgenda;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgendaPresenter implements AgendaContract.Presenter {

    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private AgendaContract.View view;
    private List<AgendaItem> agendaItemList;

    public AgendaPresenter(AgendaContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataList() {
        view.showProgress();

        Call<ResponseAgenda> call = apiInterface.getAgenda();
        call.enqueue(new Callback<ResponseAgenda>() {
            @Override
            public void onResponse(Call<ResponseAgenda> call, Response<ResponseAgenda> response) {
                view.hideProgress();
                if (response.body() != null) {
                    ResponseAgenda responseAgenda = response.body();

                    if (responseAgenda.getAgenda() != null) {
                        agendaItemList = responseAgenda.getAgenda();
                        view.showDataList(agendaItemList);
                    } else {
                        view.showPesan();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseAgenda> call, Throwable t) {
                view.hideProgress();
                view.showFailureMessage(t.getMessage());
            }
        });
    }
}

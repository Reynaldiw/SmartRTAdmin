package com.reynaldiwijaya.smartrtadmin.Presenter.Report;

import com.reynaldiwijaya.smartrtadmin.Api.ApiClient;
import com.reynaldiwijaya.smartrtadmin.Api.ApiInterface;
import com.reynaldiwijaya.smartrtadmin.Model.Report.ResponseReport;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportPresenter implements ReportContract.Presenter {
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private final ReportContract.View view;

    public ReportPresenter(ReportContract.View view) {
        this.view = view;
    }


    @Override
    public void getLaporan() {
        view.showProgress();

        Call<ResponseReport> call = apiInterface.getReport();
        call.enqueue(new Callback<ResponseReport>() {
            @Override
            public void onResponse(Call<ResponseReport> call, Response<ResponseReport> response) {
                view.hideProgress();
                if (response.body() != null) {
                    ResponseReport responseReport = response.body();

                    if (responseReport.getLaporan() != null) {
                        view.showDataList(responseReport.getLaporan());
                    }else {
                        view.showPesan();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseReport> call, Throwable t) {
                view.hideProgress();
                view.showFailureMessage(t.getMessage());
            }
        });


    }
}

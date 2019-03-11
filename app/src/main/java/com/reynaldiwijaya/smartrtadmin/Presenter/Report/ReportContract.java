package com.reynaldiwijaya.smartrtadmin.Presenter.Report;

import com.reynaldiwijaya.smartrtadmin.Model.Report.LaporanItem;

import java.util.List;

public interface ReportContract {
    interface View {
        void showProgress();
        void hideProgress();
        void showDataList(List<LaporanItem> laporanItemList);
        void showFailureMessage(String msg);
        void showPesan();
    }

    interface Presenter {
        void getLaporan();
    }
}

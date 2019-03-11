package com.reynaldiwijaya.smartrtadmin.Presenter.Agenda;

import com.reynaldiwijaya.smartrtadmin.Model.Agenda.AgendaItem;

import java.util.List;

public interface AgendaContract {
    interface View {
        void showProgress();
        void hideProgress();
        void showDataList(List<AgendaItem> agendaItemList);
        void showFailureMessage(String msg);
        void showPesan();
    }

    interface Presenter {
        void getDataList();
    }
}

package com.reynaldiwijaya.smartrtadmin.Presenter.Agenda;

public interface DetailAgendaContarct {
    interface View {
        void showProgress();
        void hideProgress();
        void showSuccesMessage(String msg);
        void showFailureMessage(String msg);
    }

    interface Presenter {
        void doConfirmation(String id);
    }
}

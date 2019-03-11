package com.reynaldiwijaya.smartrtadmin.Presenter.Store;

import com.reynaldiwijaya.smartrtadmin.Model.Store.StoreItem;

import java.util.List;

public interface StoreContract {
    interface View {
        void showProgress();
        void hideProgress();
        void showDataList(List<StoreItem> storeItemList);
        void showFailureMessage(String msg);
        void showPesan();
    }

    interface Presenter{
        void getDataStore();
    }
}

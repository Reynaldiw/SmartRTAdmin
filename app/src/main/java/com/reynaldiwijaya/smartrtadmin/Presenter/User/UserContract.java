package com.reynaldiwijaya.smartrtadmin.Presenter.User;

import com.reynaldiwijaya.smartrtadmin.Model.User.UserItem;

import java.util.List;

public interface UserContract {
    interface View {
        void showProgress();
        void hideProgress();
        void showDataUser(List<UserItem> userItemList);
        void showFailureMessage(String msg);
        void showPesan();
    }

    interface Presenter {
        void getDataUser();
    }
}

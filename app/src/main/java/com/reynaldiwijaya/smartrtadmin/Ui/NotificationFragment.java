package com.reynaldiwijaya.smartrtadmin.Ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reynaldiwijaya.smartrtadmin.R;
import com.reynaldiwijaya.smartrtadmin.View.AgendaConfirmationActivity;
import com.reynaldiwijaya.smartrtadmin.View.NewsConfirmationActivity;
import com.reynaldiwijaya.smartrtadmin.View.ReportActivity;
import com.reynaldiwijaya.smartrtadmin.View.StoreConfirmationActivity;
import com.reynaldiwijaya.smartrtadmin.View.UserConfirmationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class NotificationFragment extends Fragment {

    @BindView(R.id.cv_user)
    CardView cvUser;
    @BindView(R.id.cv_news)
    CardView cvNews;
    @BindView(R.id.cv_store)
    CardView cvStore;
    @BindView(R.id.cv_agenda)
    CardView cvAgenda;
    Unbinder unbinder;
    @BindView(R.id.cv_report)
    CardView cvReport;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.cv_user, R.id.cv_news, R.id.cv_store, R.id.cv_agenda, R.id.cv_report})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cv_user:
                startActivity(new Intent(getActivity(), UserConfirmationActivity.class));
                break;
            case R.id.cv_news:
                startActivity(new Intent(getActivity(), NewsConfirmationActivity.class));
                break;
            case R.id.cv_store:
                startActivity(new Intent(getActivity(), StoreConfirmationActivity.class));
                break;
            case R.id.cv_agenda:
                startActivity(new Intent(getActivity(), AgendaConfirmationActivity.class));
                break;
            case R.id.cv_report:
                startActivity(new Intent(getActivity(), ReportActivity.class));
                break;
        }
    }
}

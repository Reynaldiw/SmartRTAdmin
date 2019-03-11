package com.reynaldiwijaya.smartrtadmin.Ui;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.reynaldiwijaya.smartrtadmin.R;
import com.reynaldiwijaya.smartrtadmin.Utills.SessionManager;

public class SplashScreen extends AppCompatActivity {
    private SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sm = new SessionManager(this);

        setUpDelay();
    }

    private void setUpDelay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sm.checkLogin();
                finish();
            }
        },3000);

    }
}

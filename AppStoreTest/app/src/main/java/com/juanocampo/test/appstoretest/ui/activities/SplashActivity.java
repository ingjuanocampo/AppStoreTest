package com.juanocampo.test.appstoretest.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.juanocampo.test.appstoretest.R;

/**
 * Created by juanocampo on 1/30/17.
 */

public class SplashActivity extends ActivityBase {

    private static final long SPLASH_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, SPLASH_TIME);
    }

}

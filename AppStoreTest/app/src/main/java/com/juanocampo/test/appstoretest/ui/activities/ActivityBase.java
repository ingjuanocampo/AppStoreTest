package com.juanocampo.test.appstoretest.ui.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;

import com.juanocampo.test.appstoretest.R;
import com.juanocampo.test.appstoretest.util.AnimationsCommons;

/**
 * Created by juanocampo on 1/30/17.
 */

public class ActivityBase extends AppCompatActivity {

    protected void executeFragment(Fragment fragmentToExecute) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragmentToExecute)
                .commit();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (AnimationsCommons.isTablet(getApplicationContext())) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

}

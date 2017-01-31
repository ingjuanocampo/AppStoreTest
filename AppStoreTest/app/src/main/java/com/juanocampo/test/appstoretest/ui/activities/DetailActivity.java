package com.juanocampo.test.appstoretest.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.juanocampo.test.appstoretest.R;
import com.juanocampo.test.appstoretest.models.Entry;
import com.juanocampo.test.appstoretest.ui.fragments.DetailFragment;

/**
 * Created by juanocampo on 1/28/17.
 */

public class DetailActivity extends ActivityBase {

    public static final String ENTRY = "Entry";

    public static Intent newInstance(Entry entry, Context context) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(ENTRY, entry);
        return intent;
     }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().containsKey(ENTRY)) {
            Entry entry = (Entry)getIntent().getExtras().getSerializable(ENTRY);
            getSupportActionBar().setTitle((entry.getTitle().getLabel()));
            executeFragment(DetailFragment.newInstance(entry));

        }

    }
}

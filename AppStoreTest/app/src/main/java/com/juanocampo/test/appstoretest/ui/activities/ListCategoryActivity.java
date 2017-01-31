package com.juanocampo.test.appstoretest.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.juanocampo.test.appstoretest.R;
import com.juanocampo.test.appstoretest.models.Entry;
import com.juanocampo.test.appstoretest.ui.fragments.DetailFragment;
import com.juanocampo.test.appstoretest.ui.fragments.ListCategoryFragment;

import java.io.Serializable;
import java.util.List;

/**
 * Created by juanocampo on 1/30/17.
 */

public class ListCategoryActivity extends ActivityBase {


    private static final String CATEGORY = "category";
    public static final String ENTRIES = "list_categories";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().containsKey(CATEGORY)) {
            getSupportActionBar().setTitle((getIntent().getExtras().getString(CATEGORY)));
            executeFragment(ListCategoryFragment.newInstance());

        }

    }


    public static Intent newInstance(String categoryKey, List<Entry> entriesSelected, Context context) {
        Intent intent = new Intent(context, ListCategoryActivity.class);
        intent.putExtra(CATEGORY, categoryKey);
        intent.putExtra(ENTRIES, (Serializable) entriesSelected);

        return intent;
    }
}

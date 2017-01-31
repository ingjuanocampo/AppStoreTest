package com.juanocampo.test.appstoretest.ui.fragments;

import android.os.Bundle;
import android.view.View;

import com.juanocampo.test.appstoretest.ui.activities.ListCategoryActivity;
import com.juanocampo.test.appstoretest.ui.adapters.CardsAdapter;
import com.juanocampo.test.appstoretest.ui.adapters.ViewType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juanocampo on 1/30/17.
 */

public class ListCategoryFragment extends FragmentListBase {


    public static ListCategoryFragment newInstance() {
        Bundle args = new Bundle();
        ListCategoryFragment fragment = new ListCategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setVisibility(View.GONE);
        getView().post(new Runnable() {
            @Override
            public void run() {
                List<ViewType> entries = (ArrayList<ViewType>) getActivity().getIntent().getExtras().getSerializable(ListCategoryActivity.ENTRIES);
                adapter = new CardsAdapter(getActivity(), entries, ListCategoryFragment.this);
                recyclerView.setAdapter(adapter);
                recyclerView.setVisibility(View.VISIBLE);

            }
        });

    }

    @Override
    public void onCategoryClicked(String categoryKey) {
        //no-op
    }
}

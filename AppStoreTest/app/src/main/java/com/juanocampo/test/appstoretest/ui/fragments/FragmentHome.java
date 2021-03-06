package com.juanocampo.test.appstoretest.ui.fragments;

import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.View;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.juanocampo.test.appstoretest.R;
import com.juanocampo.test.appstoretest.api.ProxyApp;
import com.juanocampo.test.appstoretest.models.Entry;
import com.juanocampo.test.appstoretest.models.ServiceResponse;
import com.juanocampo.test.appstoretest.ui.activities.ListCategoryActivity;
import com.juanocampo.test.appstoretest.ui.adapters.CardsAdapter;
import com.juanocampo.test.appstoretest.ui.adapters.ViewType;

import java.util.HashMap;
import java.util.List;

import rx.Subscriber;

/**
 * Created by juanocampo on 1/28/17.
 */

public class FragmentHome extends FragmentListBase {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initializeList();
        getView().findViewById(R.id.try_again).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initializeList();
            }
        });

    }

    private void initializeList() {
        ProxyApp proxyApp = new ProxyApp(getView().getContext());

        proxyApp.getItunesInstance().getStoresSync(serviceResponseSubscriber);
        setLoader(true);
    }


    Subscriber<ServiceResponse> serviceResponseSubscriber = new Subscriber<ServiceResponse>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            setNoInternet();

        }

        @Override
        public void onNext(ServiceResponse serviceResponse) {
            setLoader(false);

            entryMap = new HashMap<>();

            for (Entry entry : serviceResponse.getFeed().getEntries()) {
                String key = entry.getCategory().getAttributes().getLabel();
                if (entryMap.containsKey(key)) {
                    entryMap.get(key).add(entry);
                } else {
                    entryMap.put(key, Lists.newArrayList(entry));
                }
            }

            List<ViewType> recyclerItems = Lists.newArrayList();
            FluentIterable.from(entryMap.keySet()).transform(new Function<String, ViewType>() {
                @Nullable
                @Override
                public ViewType apply(String input) {
                    return entryMap.get(input).get(0).getCategory();
                }
            }).copyInto(recyclerItems);

            recyclerItems.addAll(serviceResponse.getFeed().getEntries());

            adapter = new CardsAdapter(getActivity(), recyclerItems, FragmentHome.this);

            recyclerView.setAdapter(adapter);
        }
    };


    @Override
    public void onCategoryClicked(String categoryKey) {
        List<Entry> entriesSelected = entryMap.get(categoryKey);

        Intent listCategoryIntent = ListCategoryActivity.newInstance(categoryKey, entriesSelected, getActivity());


        startActivity(listCategoryIntent);
    }
}

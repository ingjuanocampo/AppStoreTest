package com.juanocampo.test.appstoretest.ui.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juanocampo.test.appstoretest.R;
import com.juanocampo.test.appstoretest.api.ProxyApp;
import com.juanocampo.test.appstoretest.models.Entry;
import com.juanocampo.test.appstoretest.models.ServiceResponse;
import com.juanocampo.test.appstoretest.ui.activities.DetailActivity;
import com.juanocampo.test.appstoretest.ui.adapters.CardsAdapter;
import com.juanocampo.test.appstoretest.util.AnimationsCommons;

import rx.Subscriber;

/**
 * Created by juanocampo on 1/28/17.
 */

public class FragmentListsStores extends Fragment implements CardsAdapter.CardAdapterActions {

    private RecyclerView recyclerView;
    private View loader;
    private View noInternet;
    private View offlineContainer;

    public static FragmentListsStores newInstance() {
        return new FragmentListsStores();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);
        noInternet = getView().findViewById(R.id.container_no_connection);
        loader = getView().findViewById(R.id.loader);
        offlineContainer = getView().findViewById(R.id.offline_container);
        if (AnimationsCommons.isTablet(getContext())) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(gridLayoutManager);

        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        }

        initializeList();

        getView().findViewById(R.id.try_again).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initializeList();
            }
        });

        IntentFilter filter = new IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION);
        try {
            getActivity().registerReceiver(networkStateReceiver, filter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initializeList() {
        ProxyApp proxyApp = new ProxyApp(getContext());

        proxyApp.getItunesInstance().getStoresSync(serviceResponseSubscriber);
        setLoader(true);
    }

    private CardsAdapter adapter;
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
            adapter = new CardsAdapter(getContext(), serviceResponse.getFeed().getEntries(), FragmentListsStores.this);

            recyclerView.setAdapter(adapter);
        }
    };


    private void setLoader(boolean visible) {
        noInternet.setVisibility(View.GONE);
        recyclerView.setVisibility(visible ? View.GONE: View.VISIBLE);
        loader.setVisibility(visible ? View.VISIBLE: View.GONE);
    }

    private void setNoInternet() {
        recyclerView.setVisibility(View.GONE);
        loader.setVisibility(View.GONE);
        noInternet.setVisibility(View.VISIBLE);
    }


    @Override
    public void onCardClicked(Entry entry, View sharedElement) {

        Pair<View, String> pair3 = Pair.create(sharedElement, sharedElement.getTransitionName());


        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(getActivity(), pair3);

        setExitTransition(new Fade());

        startActivity(DetailActivity.newInstance(entry, getActivity()), options.toBundle());
    }

    BroadcastReceiver networkStateReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean connectivity = ProxyApp.isNetworkAvailable(context);
            if (!connectivity && offlineContainer!= null) {
                offlineContainer.setVisibility(View.VISIBLE);
            } else if (offlineContainer != null){
                offlineContainer.setVisibility(View.GONE);
            }

        }
    };
}

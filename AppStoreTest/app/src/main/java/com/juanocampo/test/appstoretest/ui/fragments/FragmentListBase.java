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
import com.juanocampo.test.appstoretest.ui.activities.DetailActivity;
import com.juanocampo.test.appstoretest.ui.adapters.CardsAdapter;
import com.juanocampo.test.appstoretest.util.AnimationsCommons;

import java.util.List;
import java.util.Map;

/**
 * Created by juanocampo on 1/30/17.
 */

public abstract class FragmentListBase extends Fragment implements CardsAdapter.CardAdapterActions  {

    protected RecyclerView recyclerView;
    protected View loader;
    protected View noInternet;
    protected View offlineContainer;
    protected CardsAdapter adapter;
    protected Map<String, List<Entry>> entryMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);
        noInternet = getView().findViewById(R.id.container_no_connection);
        loader = getView().findViewById(R.id.loader);
        offlineContainer = getView().findViewById(R.id.offline_container);
        if (AnimationsCommons.isTablet(getActivity())) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(gridLayoutManager);

        } else {

            GridLayoutManager glm = new GridLayoutManager(getActivity(), 2);
            glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    switch(adapter.getItemViewType(position)){
                        case Entry.CATEGORY_VIEW_TYPE:
                            return 1;
                        case Entry.ENTRY_VIEW_TYPE:
                            return 2;
                        default:
                            return -1;
                    }
                }
            });

            recyclerView.setLayoutManager(glm);
            recyclerView.setItemViewCacheSize(20);

        }

        IntentFilter filter = new IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION);
        try {
            getActivity().registerReceiver(networkStateReceiver, filter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void setLoader(boolean visible) {
        noInternet.setVisibility(View.GONE);
        recyclerView.setVisibility(visible ? View.GONE: View.VISIBLE);
        loader.setVisibility(visible ? View.VISIBLE: View.GONE);
    }

    protected void setNoInternet() {
        recyclerView.setVisibility(View.GONE);
        loader.setVisibility(View.GONE);
        noInternet.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCardClicked(Entry entry, View sharedElement) {

        Pair<View, String> pair3 = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            pair3 = Pair.create(sharedElement, sharedElement.getTransitionName());
            setExitTransition(new Fade());
        }

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(getActivity(), pair3);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            getView().getContext().startActivity(DetailActivity.newInstance(entry, getActivity()), options.toBundle());
        } else {
            getView().getContext().startActivity(DetailActivity.newInstance(entry, getActivity()));
        }
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

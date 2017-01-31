package com.juanocampo.test.appstoretest.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by juanocampo
 */
public class ProxyApp {

    private final Context context;
    private Itunes itunes;

    public ProxyApp(Context context) {
        this.context = context;
    }

    public Itunes getItunesInstance() {
        if (isNetworkAvailable(context)) {
            itunes = new ItunesApiClientImp(context);
        } else {
            itunes = new ItunesCacheImp(context);
        }
     return itunes;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}

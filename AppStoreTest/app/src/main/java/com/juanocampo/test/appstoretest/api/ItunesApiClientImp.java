package com.juanocampo.test.appstoretest.api;

import android.content.Context;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.juanocampo.test.appstoretest.models.ServiceResponse;
import com.juanocampo.test.appstoretest.util.GsonConverter;
import com.juanocampo.test.appstoretest.util.SharePreferenceManager;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.observables.AsyncOnSubscribe;
import rx.schedulers.Schedulers;

/**
 * Created by juanocampo
 */
public class ItunesApiClientImp implements Itunes{

    private static final java.lang.String STORE_CACHE = "STORE";
    private static String urlBase = "https://itunes.apple.com";


    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private final ItunesApiClient itunesApiClient;
    private final SharePreferenceManager mem;


    public ItunesApiClientImp(Context context) {
        itunesApiClient = retrofit.create(ItunesApiClient.class);
        mem = SharePreferenceManager.getInstance(context);
    }

    @Override
    public void getStoresSync(final Subscriber<ServiceResponse> responsesCalls) {

        Observable.create(new Observable.OnSubscribe<ServiceResponse>() {
            @Override
            public void call(final Subscriber<? super ServiceResponse> subscriber) {
                final Call<ServiceResponse> request = itunesApiClient.getApps();
                request.enqueue(new Callback<ServiceResponse>() {
                    @Override
                    public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                        if (response.isSuccessful()) {
                            subscriber.onNext(response.body());
                            mem.putInMemory(ItunesCacheImp.RESPONSE_SAVE, GsonConverter.object2StringGson(response.body()));
                        }
                    }

                    @Override
                    public void onFailure(Call<ServiceResponse> call, Throwable t) {
                        responsesCalls.onError(new Exception("Something when wrong"));
                    }
                });
            }
        }).subscribe(responsesCalls);
    }
}

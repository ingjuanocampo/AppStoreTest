package com.juanocampo.test.appstoretest.api;

import android.content.Context;
import android.os.AsyncTask;

import com.juanocampo.test.appstoretest.models.ServiceResponse;
import com.juanocampo.test.appstoretest.util.GsonConverter;
import com.juanocampo.test.appstoretest.util.SharePreferenceManager;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by juanocampo
 */
public class ItunesCacheImp implements Itunes {

    private final SharePreferenceManager mem;
    public static final String RESPONSE_SAVE = "Response_saved";

    public ItunesCacheImp(Context context) {
        mem = SharePreferenceManager.getInstance(context);
    }

    @Override
    public void getStoresSync(final Subscriber<ServiceResponse> subscriber) {

        new AsyncTask<Void, Void, ServiceResponse>() {
            @Override
            protected ServiceResponse doInBackground(Void... params) {
                String jsonToParcer = mem.getStringMemory(RESPONSE_SAVE, null);
                return GsonConverter.gsonToFeed(jsonToParcer);
            }

            @Override
            protected void onPostExecute(ServiceResponse serviceResponse) {
                super.onPostExecute(serviceResponse);
                if (serviceResponse != null) {
                    subscriber.onNext(serviceResponse);
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new Exception("There is not internet connection neither stored data"));
                }
            }
        }.execute();

    }

}

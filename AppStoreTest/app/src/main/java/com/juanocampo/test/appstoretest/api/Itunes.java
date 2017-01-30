package com.juanocampo.test.appstoretest.api;

import com.juanocampo.test.appstoretest.models.ServiceResponse;

import rx.Subscriber;

/**
 * Created by juanocampo
 */
public interface Itunes {

    void getStoresSync(Subscriber<ServiceResponse> serviceResponseSubscriber);
}

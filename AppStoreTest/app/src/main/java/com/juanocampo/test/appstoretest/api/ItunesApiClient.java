package com.juanocampo.test.appstoretest.api;


import com.juanocampo.test.appstoretest.models.ServiceResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ItunesApiClient {

    @GET("/us/rss/topfreeapplications/limit=20/json")
    Call<ServiceResponse> getApps();
}

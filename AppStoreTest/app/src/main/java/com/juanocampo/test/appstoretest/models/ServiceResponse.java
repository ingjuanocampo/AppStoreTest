package com.juanocampo.test.appstoretest.models;

/**
 * Created by juanocampo on 6/20/16.
 */
public class ServiceResponse {

    private final Feed feed;

    public ServiceResponse(Feed feed) {
        this.feed = feed;
    }

    public Feed getFeed() {
        return feed;
    }
}

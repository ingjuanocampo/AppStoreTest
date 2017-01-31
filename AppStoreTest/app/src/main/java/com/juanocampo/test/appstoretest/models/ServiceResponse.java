package com.juanocampo.test.appstoretest.models;

/**
 * Created by juanocampo
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

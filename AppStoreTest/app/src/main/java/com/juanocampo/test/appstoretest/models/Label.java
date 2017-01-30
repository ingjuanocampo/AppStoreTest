package com.juanocampo.test.appstoretest.models;

import java.io.Serializable;

/**
 * Created by juanocampo on 6/16/16.
 */
public class Label implements Serializable {
    private final String label;

    public Label(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
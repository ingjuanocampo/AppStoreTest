package com.juanocampo.test.appstoretest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by juanocampo
 */
public class Author implements Serializable {

    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("uri")
    @Expose
    private Uri uri;

    /**
     * @return The name
     */
    public Name getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * @return The uri
     */
    public Uri getUri() {
        return uri;
    }

    /**
     * @param uri The uri
     */
    public void setUri(Uri uri) {
        this.uri = uri;
    }


    public class Name implements Serializable {

        @SerializedName("label")
        @Expose
        private String label;

        /**
         * @return The label
         */
        public String getLabel() {
            return label;
        }

        /**
         * @param label The label
         */
        public void setLabel(String label) {
            this.label = label;
        }

    }


    public class Uri implements Serializable {

        @SerializedName("label")
        @Expose
        private String label;

        /**
         * @return The label
         */
        public String getLabel() {
            return label;
        }

        /**
         * @param label The label
         */
        public void setLabel(String label) {
            this.label = label;
        }

    }

}



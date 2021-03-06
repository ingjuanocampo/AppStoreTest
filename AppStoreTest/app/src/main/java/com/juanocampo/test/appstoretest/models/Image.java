package com.juanocampo.test.appstoretest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by juanocampo
 */
public class Image implements Serializable {

    public class Attributes {

        @SerializedName("height")
        @Expose
        private String height;

        /**
         * @return The height
         */
        public String getHeight() {
            return height;
        }

        /**
         * @param height The height
         */
        public void setHeight(String height) {
            this.height = height;
        }
    }
}

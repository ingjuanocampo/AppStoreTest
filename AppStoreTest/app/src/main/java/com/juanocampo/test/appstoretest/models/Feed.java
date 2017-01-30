package com.juanocampo.test.appstoretest.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by juanocampo on 6/14/16.
 */
public class Feed implements Serializable {

    private final Author author;
    @SerializedName("entry")
    private final List<Entry> entries;
    private final Label updated;
    private final Label rights;
    private final Label title;
    private final Label icon;

    public Feed(Author author, List<Entry> entries, Label updated, Label rights, Label title, Label icon) {
        this.author = author;
        this.entries = entries;
        this.updated = updated;
        this.rights = rights;
        this.title = title;
        this.icon = icon;
    }

    public Author getAuthor() {
        return author;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public Label getUpdated() {
        return updated;
    }

    public Label getRights() {
        return rights;
    }

    public Label getTitle() {
        return title;
    }

    public Label getIcon() {
        return icon;
    }
}

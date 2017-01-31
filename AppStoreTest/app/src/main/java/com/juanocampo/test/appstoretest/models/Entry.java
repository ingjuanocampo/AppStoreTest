package com.juanocampo.test.appstoretest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.juanocampo.test.appstoretest.ui.adapters.ViewType;

import java.io.Serializable;
import java.util.List;

/**
 * Created by juanocampo
 */
public class Entry implements Serializable, ViewType {

    public static final int ENTRY_VIEW_TYPE = 1;
    public static final int CATEGORY_VIEW_TYPE = 2;
    @SerializedName("im:name")
    private final Label name;
    @SerializedName("im:image")
    private final List<Image> imagesList;
    private final Label summary;
    @SerializedName("im:price")
    private final Price price;
    @SerializedName("im:contentType")
    private final ContentType contentType;
    private final Label rights;
    private final Label title;
    private final Link link;
    private final Category category;
    @SerializedName("im:releaseDate")
    private final ReleaseDate releaseDate;

    public Label getName() {
        return name;
    }

    public List<Image> getImagesList() {
        return imagesList;
    }

    public Label getSummary() {
        return summary;
    }

    public Price getPrice() {
        return price;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public Label getRights() {
        return rights;
    }

    public Label getTitle() {
        return title;
    }

    public Link getLink() {
        return link;
    }

    public Category getCategory() {
        return category;
    }

    public ReleaseDate getReleaseDate() {
        return releaseDate;
    }

    protected Entry(Label name, List<Image> imagesList, Label summary, Price price, ContentType contentType, Label rights, Label title, Link link, Category category, ReleaseDate releaseDate) {
        this.name = name;
        this.imagesList = imagesList;
        this.summary = summary;
        this.price = price;
        this.contentType = contentType;
        this.rights = rights;
        this.title = title;
        this.link = link;
        this.category = category;
        this.releaseDate = releaseDate;
    }

    @Override
    public int getViewType() {
        return ENTRY_VIEW_TYPE;
    }

    public class ReleaseDate implements Serializable {
        private final Label attributes;

        private ReleaseDate(Label attributes) {
            this.attributes = attributes;
        }

        public Label getAttributes() {
            return attributes;
        }
    }

    public class Category implements Serializable, ViewType {
        private final CategoryAttributes attributes;

        public Category(CategoryAttributes attributes) {
            this.attributes = attributes;
        }

        public CategoryAttributes getAttributes() {
            return attributes;
        }

        @Override
        public int getViewType() {
            return CATEGORY_VIEW_TYPE;
        }
    }

    public class CategoryAttributes implements Serializable {
        @SerializedName("im:id")
        private final String id;
        private final String term;
        private final String scheme;
        private final String label;

        public CategoryAttributes(String id, String term, String scheme, String label) {
            this.id = id;
            this.term = term;
            this.scheme = scheme;
            this.label = label;
        }

        public String getId() {
            return id;
        }

        public String getTerm() {
            return term;
        }

        public String getScheme() {
            return scheme;
        }

        public String getLabel() {
            return label;
        }
    }

    public class Link implements Serializable {
        private final LinkAttributes attributes;

        private Link(LinkAttributes attributes) {
            this.attributes = attributes;
        }

        public LinkAttributes getAttributes() {
            return attributes;
        }
    }

    public class LinkAttributes implements Serializable {
        private final String rel;
        private final String type;
        private final String href;

        private LinkAttributes(String rel, String type, String href) {
            this.rel = rel;
            this.type = type;
            this.href = href;
        }

        public String getRel() {
            return rel;
        }

        public String getType() {
            return type;
        }

        public String getHref() {
            return href;
        }
    }

    public class ContentType implements Serializable {
        @SerializedName("attributes")
        private final ContentTypeAttributes contentTypeAttributes;

        public ContentType(ContentTypeAttributes contentTypeAttributes) {
            this.contentTypeAttributes = contentTypeAttributes;
        }

        public ContentTypeAttributes getContentTypeAttributes() {
            return contentTypeAttributes;
        }
    }

    public class ContentTypeAttributes implements Serializable {
        private final String term;
        private final String label;

        public ContentTypeAttributes(String term, String label) {
            this.term = term;
            this.label = label;
        }

        public String getTerm() {
            return term;
        }

        public String getLabel() {
            return label;
        }
    }

    public class Price implements Serializable {
        private final String label;
        @SerializedName("attributes")
        private final PriceAttributes priceAttributes;

        public Price(String label, PriceAttributes priceAttributes) {
            this.label = label;
            this.priceAttributes = priceAttributes;
        }

        public String getLabel() {
            return label;
        }

        public PriceAttributes getPriceAttributes() {
            return priceAttributes;
        }
    }

    public class PriceAttributes implements Serializable {
        private final String amount;
        private final String currency;


        public PriceAttributes(String amount, String currency) {
            this.amount = amount;
            this.currency = currency;
        }

        public String getAmount() {
            return amount;
        }

        public String getCurrency() {
            return currency;
        }
    }


    public class Image implements Serializable {
        private final String label;
        private final Attributes attributes;


        public Image(String label, Attributes attributes) {
            this.label = label;
            this.attributes = attributes;
        }

        public String getLabel() {
            return label;
        }

        public Attributes getAttributes() {
            return attributes;
        }
    }

    public class Attributes implements Serializable {

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

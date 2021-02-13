package com.alchemy.api.models;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "Article")

public class Article {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "title")
    private String title;
    ///@Column(name = "rawDetails")
    ///private Blob rawDetails;
    @Column(name = "details")
    private String details;
    @Column(name = "url")
    private String url;

    public String getId() { return id; }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
//    public Blob getRawDetails() {
//        return rawDetails;
//    }
//
//    public void setRawDetails(Blob rawDetails) {
//        this.rawDetails = rawDetails;
//    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

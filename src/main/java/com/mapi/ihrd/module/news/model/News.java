package com.mapi.ihrd.module.news.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mapi.ihrd.module.aauth.model.User;
import com.mapi.ihrd.model.BaseModel;
import com.mapi.ihrd.config.Constant;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = Constant.TABLE_NAME_NEWS)
public class News extends BaseModel {

    public News() {

    }

    public News(String id) {
        setId(id);
    }

    @NotNull
    @NotEmpty
    @Column(name = "title")
    private String title;

    @NotNull
    @NotEmpty
    @Column(name = "content")
    private String content;

    @Column(name = "headline")
    private String headline;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "post_date")
    private Date postDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User postBy;

    @Column(name = "image_url")
    private String imageUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public User getPostBy() {
        return postBy;
    }

    public void setPostBy(User postBy) {
        this.postBy = postBy;
    }
}

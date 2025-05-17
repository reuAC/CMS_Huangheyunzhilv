package org.reuac.yrt_001.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Article {
    private int articleId;
    private String articleSlug;
    private String title;
    private String author;
    private Date publishDate;
    private String estimatedReadTime;
    private String featuredImageUrl;
    private String leadParagraph;
    private String contentHtml;
    private String pageTitleSeo;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private List<Tag> tags;


    public Article() {
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getArticleSlug() {
        return articleSlug;
    }

    public void setArticleSlug(String articleSlug) {
        this.articleSlug = articleSlug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getEstimatedReadTime() {
        return estimatedReadTime;
    }

    public void setEstimatedReadTime(String estimatedReadTime) {
        this.estimatedReadTime = estimatedReadTime;
    }

    public String getFeaturedImageUrl() {
        return featuredImageUrl;
    }

    public void setFeaturedImageUrl(String featuredImageUrl) {
        this.featuredImageUrl = featuredImageUrl;
    }

    public String getLeadParagraph() {
        return leadParagraph;
    }

    public void setLeadParagraph(String leadParagraph) {
        this.leadParagraph = leadParagraph;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public String getPageTitleSeo() {
        return pageTitleSeo;
    }

    public void setPageTitleSeo(String pageTitleSeo) {
        this.pageTitleSeo = pageTitleSeo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
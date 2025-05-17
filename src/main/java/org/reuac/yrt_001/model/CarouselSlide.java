package org.reuac.yrt_001.model;

import java.sql.Timestamp;

public class CarouselSlide {
    private int slideId;
    private String pageCode;
    private String imageUrl;
    private String imageAltText;
    private String captionTitle;
    private String captionText;
    private String linkUrl;
    private String linkedArticleSlug;
    private int displayOrder;
    private boolean isActive;
    private Timestamp createdAt;
    private Timestamp updatedAt;


    public CarouselSlide() {
    }


    public int getSlideId() {
        return slideId;
    }

    public void setSlideId(int slideId) {
        this.slideId = slideId;
    }

    public String getPageCode() {
        return pageCode;
    }

    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageAltText() {
        return imageAltText;
    }

    public void setImageAltText(String imageAltText) {
        this.imageAltText = imageAltText;
    }

    public String getCaptionTitle() {
        return captionTitle;
    }

    public void setCaptionTitle(String captionTitle) {
        this.captionTitle = captionTitle;
    }

    public String getCaptionText() {
        return captionText;
    }

    public void setCaptionText(String captionText) {
        this.captionText = captionText;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getLinkedArticleSlug() {
        return linkedArticleSlug;
    }

    public void setLinkedArticleSlug(String linkedArticleSlug) {
        this.linkedArticleSlug = linkedArticleSlug;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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


    public boolean getIsActive() {
        return isActive;
    }
}
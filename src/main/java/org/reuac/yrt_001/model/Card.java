package org.reuac.yrt_001.model;

import java.sql.Timestamp;

public class Card {
    private int cardId;
    private Integer sectionId;
    private String pageCode;
    private String cardType;
    private String title;
    private String subtitle;
    private String imageUrl;
    private String imageAltText;
    private String placeholderLogoText;
    private String linkUrl;
    private String linkText;
    private String metaInfo1;
    private String metaInfo2;
    private int displayOrder;
    private String iconSvg;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String linkedArticleSlug;


    public Card() {
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getPageCode() {
        return pageCode;
    }

    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
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

    public String getPlaceholderLogoText() {
        return placeholderLogoText;
    }

    public void setPlaceholderLogoText(String placeholderLogoText) {
        this.placeholderLogoText = placeholderLogoText;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getLinkText() {
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    public String getMetaInfo1() {
        return metaInfo1;
    }

    public void setMetaInfo1(String metaInfo1) {
        this.metaInfo1 = metaInfo1;
    }

    public String getMetaInfo2() {
        return metaInfo2;
    }

    public void setMetaInfo2(String metaInfo2) {
        this.metaInfo2 = metaInfo2;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getIconSvg() {
        return iconSvg;
    }

    public void setIconSvg(String iconSvg) {
        this.iconSvg = iconSvg;
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

    public String getLinkedArticleSlug() {
        return linkedArticleSlug;
    }

    public void setLinkedArticleSlug(String linkedArticleSlug) {
        this.linkedArticleSlug = linkedArticleSlug;
    }
}
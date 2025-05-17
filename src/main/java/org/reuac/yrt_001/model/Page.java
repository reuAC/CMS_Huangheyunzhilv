package org.reuac.yrt_001.model;

import java.sql.Timestamp;

public class Page {
    private int pageId;
    private String pageCode;
    private String pageTitleSeo;
    private String heroTitle;
    private String heroSubtitle;
    private String heroSearchPlaceholder;
    private String heroImageUrl;
    private String heroImageAltText;
    private Timestamp createdAt;
    private Timestamp updatedAt;


    public Page() {
    }


    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public String getPageCode() {
        return pageCode;
    }

    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
    }

    public String getPageTitleSeo() {
        return pageTitleSeo;
    }

    public void setPageTitleSeo(String pageTitleSeo) {
        this.pageTitleSeo = pageTitleSeo;
    }

    public String getHeroTitle() {
        return heroTitle;
    }

    public void setHeroTitle(String heroTitle) {
        this.heroTitle = heroTitle;
    }

    public String getHeroSubtitle() {
        return heroSubtitle;
    }

    public void setHeroSubtitle(String heroSubtitle) {
        this.heroSubtitle = heroSubtitle;
    }

    public String getHeroSearchPlaceholder() {
        return heroSearchPlaceholder;
    }

    public void setHeroSearchPlaceholder(String heroSearchPlaceholder) {
        this.heroSearchPlaceholder = heroSearchPlaceholder;
    }

    public String getHeroImageUrl() {
        return heroImageUrl;
    }

    public void setHeroImageUrl(String heroImageUrl) {
        this.heroImageUrl = heroImageUrl;
    }

    public String getHeroImageAltText() {
        return heroImageAltText;
    }

    public void setHeroImageAltText(String heroImageAltText) {
        this.heroImageAltText = heroImageAltText;
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

    @Override
    public String toString() {
        return "Page{" +
                "pageId=" + pageId +
                ", pageCode='" + pageCode + '\'' +
                ", pageTitleSeo='" + pageTitleSeo + '\'' +
                ", heroTitle='" + heroTitle + '\'' +
                '}';
    }
}
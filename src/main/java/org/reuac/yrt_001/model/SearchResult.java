package org.reuac.yrt_001.model;

public class SearchResult {
    private String title;
    private String metaInfo;
    private String snippetHtml;
    private String linkUrl;
    private String displayUrlFragment;

    public SearchResult() {
    }

    public SearchResult(String title, String metaInfo, String snippetHtml, String linkUrl, String displayUrlFragment) {
        this.title = title;
        this.metaInfo = metaInfo;
        this.snippetHtml = snippetHtml;
        this.linkUrl = linkUrl;
        this.displayUrlFragment = displayUrlFragment;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(String metaInfo) {
        this.metaInfo = metaInfo;
    }

    public String getSnippetHtml() {
        return snippetHtml;
    }

    public void setSnippetHtml(String snippetHtml) {
        this.snippetHtml = snippetHtml;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getDisplayUrlFragment() {
        return displayUrlFragment;
    }

    public void setDisplayUrlFragment(String displayUrlFragment) {
        this.displayUrlFragment = displayUrlFragment;
    }
}
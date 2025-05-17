package org.reuac.yrt_001.model;

import java.sql.Timestamp;

public class NavigationItem {
    private int navItemId;
    private String navArea;
    private String text;
    private String url;
    private String targetPageCode;
    private int displayOrder;
    private String iconClass;
    private boolean isButton;
    private String buttonClass;
    private String textBeforeButton;
    private Timestamp createdAt;


    public NavigationItem() {
    }


    public int getNavItemId() {
        return navItemId;
    }

    public void setNavItemId(int navItemId) {
        this.navItemId = navItemId;
    }

    public String getNavArea() {
        return navArea;
    }

    public void setNavArea(String navArea) {
        this.navArea = navArea;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTargetPageCode() {
        return targetPageCode;
    }

    public void setTargetPageCode(String targetPageCode) {
        this.targetPageCode = targetPageCode;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    public boolean getIsButton() {
        return isButton;
    }

    public void setIsButton(boolean button) {
        isButton = button;
    }

    public boolean isButton() {
        return isButton;
    }

    public String getButtonClass() {
        return buttonClass;
    }

    public void setButtonClass(String buttonClass) {
        this.buttonClass = buttonClass;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getTextBeforeButton() {
        return textBeforeButton;
    }

    public void setTextBeforeButton(String textBeforeButton) {
        this.textBeforeButton = textBeforeButton;
    }
}
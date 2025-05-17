<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <c:set var="pageSpecificCss" value="shiciyou.css" scope="request"/>
    <jsp:include page="common/_head.jsp"/>
</head>
<body>
<div class="page-wrapper">
    <jsp:include page="common/_header_nav.jsp"/>
    <jsp:include page="common/_hero_search.jsp"/>

    <div class="basic page-content guides-section">
        <div class="container">
            <div class="introduction-section">
                <h2 class="main-heading"><c:out value="${guidesSection.title}"
                                                default="Our expert guides to choosing your honeymoon"/></h2>
                <p class="descriptive-paragraph">
                    <c:out value="${guidesSection.subtitle}"
                           default="Written by our experts based on their own travels, these guides are designed to help you choose where to go on honeymoon, what to do and where to stay."/>
                </p>
            </div>

            <div class="guides-grid-container">
                <c:forEach items="${guideCards}" var="card">
                    <div class="guide-card js-clickable-card"
                         data-article-slug="<c:out value='${card.linkedArticleSlug}'/>">
                        <div class="guide-card-image">
                            <img src="${pageContext.request.contextPath}/${card.imageUrl}"
                                 alt="<c:out value='${card.imageAltText}'/>">
                            <div class="image-overlay-title"><c:out value="${card.title}"/></div>
                        </div>
                        <div class="guide-card-content">
                            <p class="guide-description"><c:out value="${card.subtitle}"/></p>
                            <div class="guide-meta">
                                <span class="read-time"><c:out value="${card.metaInfo1}" default="READ"/></span>
                                <a href="${pageContext.request.contextPath}/${not empty card.linkedArticleSlug ? 'article/' : ''}${not empty card.linkedArticleSlug ? card.linkedArticleSlug : card.linkUrl}"
                                   class="read-guide-link">
                                    <c:out value="${card.linkText}" default="READ THIS GUIDE"/>
                                </a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<jsp:include page="common/_footer.jsp"/>
<script src="${pageContext.request.contextPath}/js/card_click_handler.js" defer></script>
<script src="${pageContext.request.contextPath}/js/search_handler.js" defer></script>
</body>
</html>
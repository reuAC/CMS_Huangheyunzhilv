<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <c:set var="pageSpecificCss" value="zijiayou.css" scope="request"/>
    <jsp:include page="common/_head.jsp"/>
</head>
<body>
<div class="page-wrapper">
    <jsp:include page="common/_header_nav.jsp"/>
    <jsp:include page="common/_hero_search.jsp"/>

    <div class="basic page-content self-drive-section">
        <div class="container">
            <div class="introduction-section">
                <h2 class="main-heading"><c:out value="${roadTripSection.title}"
                                                default="Plan your perfect road trip"/></h2>
                <p class="descriptive-paragraph">
                    <c:out value="${roadTripSection.subtitle}"
                           default="Discover scenic routes, hidden gems, and unforgettable experiences. We provide a variety of self-drive itineraries designed for adventure and freedom."/>
                </p>
            </div>

            <div class="ideas-grid-container self-drive-grid-row1">
                <c:forEach items="${roadtripCardsRow1}" var="card">
                    <div class="idea-card js-clickable-card"
                         data-article-slug="<c:out value='${card.linkedArticleSlug}'/>">
                        <div class="image-area">
                            <c:choose>
                                <c:when test="${not empty card.imageUrl}">
                                    <img src="${pageContext.request.contextPath}/${card.imageUrl}"
                                         alt="<c:out value='${card.imageAltText}' default='${card.title}'/>">
                                </c:when>
                                <c:otherwise>
                                    <div class="image-placeholder-fallback">
                                        <span class="placeholder-logo"><c:out value="${card.placeholderLogoText}"
                                                                              default="路"/></span>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="caption-area">
                            <p class="card-text"><c:out value="${card.title}"/></p>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div class="ideas-grid-container self-drive-grid-row-more">
                <c:forEach items="${roadtripCardsRowMore}" var="card">
                    <div class="idea-card js-clickable-card"
                         data-article-slug="<c:out value='${card.linkedArticleSlug}'/>">
                        <div class="image-area">
                            <c:choose>
                                <c:when test="${not empty card.imageUrl}">
                                    <img src="${pageContext.request.contextPath}/${card.imageUrl}"
                                         alt="<c:out value='${card.imageAltText}' default='${card.title}'/>">
                                </c:when>
                                <c:otherwise>
                                    <div class="image-placeholder-fallback">
                                        <span class="placeholder-logo"><c:out value="${card.placeholderLogoText}"
                                                                              default="景"/></span>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="caption-area">
                            <p class="card-text"><c:out value="${card.title}"/></p>
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
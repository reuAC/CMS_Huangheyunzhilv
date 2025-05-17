<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <c:set var="pageSpecificCss" value="jinpinyou.css" scope="request"/>
    <jsp:include page="common/_head.jsp"/>
</head>
<body>
<div class="page-wrapper">
    <jsp:include page="common/_header_nav.jsp"/>
    <jsp:include page="common/_hero_search.jsp"/>
    <div class="basic page-content boutique-ideas-section">
        <div class="container">
            <div class="introduction-section">
                <h2 class="main-heading"><c:out value="${tripIdeasSection.title}"
                                                default="Trip ideas to match your interests"/></h2>
                <p class="descriptive-paragraph">
                    <c:out value="${tripIdeasSection.subtitle}"
                           default="Whether you’re looking for a particular style of accommodation, have a specific interest – from wildlife to art or the great outdoors – or a special occasion to celebrate, we can tailor-make an itinerary to match."/>
                </p>
            </div>

            <div class="ideas-grid-container">
                <c:forEach items="${ideaCards}" var="card">
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
                                                                              default="黄"/></span>
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
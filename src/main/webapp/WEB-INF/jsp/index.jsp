<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <jsp:include page="common/_head.jsp"/>
</head>
<body>
<div class="page-wrapper">
    <jsp:include page="common/_header_nav.jsp"/>
    <jsp:include page="common/_hero_search.jsp"/>

    <div class="basic special-sights">
        <div class="container">
            <div class="sights-header">
                <h2 class="main-title"><c:out value="${specialSightsSection.title}" default="特别景色"/></h2>
                <p class="subtitle"><c:out value="${specialSightsSection.subtitle}"
                                           default="浏览这些灵感页面，开始规划您的下一次冒险。我们安排前往各大洲80多个目的地的旅行。"/></p>
            </div>

            <div class="sights-grid">
                <div class="grid-row featured-row">
                    <c:forEach items="${featuredSightsCards}" var="card">
                        <div class="grid-item large js-clickable-card"
                             data-article-slug="<c:out value='${card.linkedArticleSlug}'/>">
                            <img src="${pageContext.request.contextPath}/${card.imageUrl}"
                                 alt="<c:out value='${card.imageAltText}'/>">
                            <div class="overlay-text">
                                <h3><c:out value="${card.title}"/></h3>
                                <p><c:out value="${card.subtitle}"/></p>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <div class="grid-row standard-row">
                    <c:forEach items="${standardSightsCardsRow1}" var="card">
                        <div class="grid-item small js-clickable-card"
                             data-article-slug="<c:out value='${card.linkedArticleSlug}'/>">
                            <img src="${pageContext.request.contextPath}/${card.imageUrl}"
                                 alt="<c:out value='${card.imageAltText}'/>">
                            <div class="overlay-text-bottom"><c:out value="${card.title}"/></div>
                        </div>
                    </c:forEach>
                </div>

                <div class="grid-row standard-row">
                    <c:forEach items="${standardSightsCardsRow2}" var="card">
                        <div class="grid-item small js-clickable-card"
                             data-article-slug="<c:out value='${card.linkedArticleSlug}'/>">
                            <img src="${pageContext.request.contextPath}/${card.imageUrl}"
                                 alt="<c:out value='${card.imageAltText}'/>">
                            <div class="overlay-text-bottom"><c:out value="${card.title}"/></div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

    <div class="basic content-feature-section">
        <div class="container">
            <div class="section-header">
                <h2 class="section-title"><c:out value="${serviceFeaturesSection.title}" default="我们的服务特色"/></h2>
                <p class="section-subtitle"><c:out value="${serviceFeaturesSection.subtitle}"
                                                   default="我们致力于提供独特且难忘的黄河文化旅游体验。"/></p>
            </div>

            <div class="feature-cards-grid">
                <c:forEach items="${serviceFeatureCards}" var="card">
                    <div class="feature-card js-clickable-card"
                         data-article-slug="<c:out value='${card.linkedArticleSlug}'/>">
                        <div class="card-icon">
                                ${card.iconSvg}
                        </div>
                        <h3 class="card-title"><c:out value="${card.title}"/></h3>
                        <p class="card-description"><c:out value="${card.subtitle}"/></p>
                        <a href="${pageContext.request.contextPath}/${not empty card.linkedArticleSlug ? 'article/' : ''}${not empty card.linkedArticleSlug ? card.linkedArticleSlug : card.linkUrl}"
                           class="card-link">
                            <c:out value="${card.linkText}" default="了解更多 →"/>
                        </a>
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
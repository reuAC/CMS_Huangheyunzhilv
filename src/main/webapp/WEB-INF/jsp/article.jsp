<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><c:out value="${article.pageTitleSeo}" default="文章详情 - 黄河云之旅"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/article.css">
</head>
<body>
<div class="page-wrapper">
    <div class="basic article-page-nav">
        <div class="container article-nav-container">
            <div class="nav-left">
                <a href="javascript:history.back()" class="back-button">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                         stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                         class="icon-arrow-left">
                        <polyline points="15 18 9 12 15 6"></polyline>
                    </svg>
                    <span>返回</span>
                </a>
            </div>
            <div class="nav-center article-nav-title-placeholder">
                <c:out value="${article.title}"/>
            </div>
            <div class="nav-right">
            </div>
        </div>
    </div>

    <div class="basic page-content article-page">
        <div class="container article-container">

            <header class="article-header">
                <h1 id="mainArticleTitle" class="article-title"><c:out value="${article.title}"/></h1>
                <div class="article-meta">
                    <span class="meta-item author">作者：<c:out value="${article.author}" default="佚名"/></span>
                    <span class="meta-item date">发布于：
    <c:choose>
        <c:when test="${not empty article.publishDate}">
            <fmt:formatDate value="${article.publishDate}" pattern="yyyy年MM月dd日"/>
        </c:when>
        <c:otherwise>
            -
        </c:otherwise>
    </c:choose>
</span>
                    <span class="meta-item read-time">预计阅读时间：<c:out value="${article.estimatedReadTime}"
                                                                          default="N/A"/></span>
                </div>
                <c:if test="${not empty article.featuredImageUrl}">
                    <div class="article-featured-image">
                        <img src="${pageContext.request.contextPath}/${article.featuredImageUrl}"
                             alt="<c:out value='${article.title}'/> 特色图">
                    </div>
                </c:if>
            </header>

            <article class="article-content">
                <p class="lead-paragraph"><c:out value="${article.leadParagraph}"/></p>
                ${article.contentHtml}
            </article>

            <footer class="article-footer">
                <div class="article-tags">
                    <span>标签：</span>
                    <c:forEach items="${articleTags}" var="tag">
                        <a href="${pageContext.request.contextPath}/search?tag=${tag.tagSlug}" class="tag-link"><c:out
                                value="${tag.tagName}"/></a>
                    </c:forEach>
                </div>
                <div class="article-share">
                    <span>分享到：</span>
                    <a href="#" class="share-link">微信</a>
                    <a href="#" class="share-link">微博</a>
                    <a href="#" class="share-link">复制链接</a>
                </div>
            </footer>

            <section class="article-comments">
                <h3>参与讨论</h3>
                <p>期待你的精彩评论...</p>
            </section>

        </div>
    </div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const articleNav = document.querySelector('.article-page-nav');
        const articleTitlePlaceholder = document.querySelector('.article-nav-title-placeholder');
        const mainArticleTitleEl = document.getElementById('mainArticleTitle');
        let lastScrollTop = 0;

        if (mainArticleTitleEl && articleTitlePlaceholder && articleTitlePlaceholder.textContent.trim() === '') {
            articleTitlePlaceholder.textContent = mainArticleTitleEl.textContent;
        }

        window.addEventListener('scroll', function () {
            let scrollTop = window.pageYOffset || document.documentElement.scrollTop;
            if (scrollTop > lastScrollTop && scrollTop > (articleNav ? articleNav.offsetHeight : 60)) {
                if (articleNav) articleNav.style.top = '-' + (articleNav.offsetHeight + 5) + 'px';
            } else {
                if (articleNav) articleNav.style.top = '0';
            }
            lastScrollTop = scrollTop <= 0 ? 0 : scrollTop;
        }, false);
    });
</script>
</body>
</html>
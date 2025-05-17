<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<footer class="basic site-footer">
    <div class="container">
        <hr class="footer-divider">
        <nav class="footer-nav">
            <ul>
                <c:forEach items="${footerNavItems}" var="navItem">
                    <li>
                        <c:set var="isArticleLink" value="${fn:startsWith(navItem.url, 'article/')}"/>
                        <c:set var="linkSlug" value=""/>
                        <c:if test="${isArticleLink}">
                            <c:set var="linkSlug" value="${fn:substringAfter(navItem.url, 'article/')}"/>
                        </c:if>

                        <c:set var="isActive" value="false"/>
                        <c:if test="${isArticleLink && (pageData.pageCode == 'article' || pageData.pageCode == 'article_default' || not empty pageTitleSeoOverride) && not empty article && article.articleSlug == linkSlug}">
                            <c:set var="isActive" value="true"/>
                        </c:if>
                        <c:if test="${!isArticleLink && navItem.targetPageCode == pageData.pageCode}">
                            <c:set var="isActive" value="true"/>
                        </c:if>

                        <a href="${pageContext.request.contextPath}/${navItem.url}" class="${isActive ? 'active' : ''}">
                            <c:out value="${navItem.text}"/>
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
        <div class="footer-copyright">
            <p><c:out value="${footerCopyrightText}" default="© 2025 黄河云之旅. 版权所有."/></p>
        </div>
    </div>
</footer>
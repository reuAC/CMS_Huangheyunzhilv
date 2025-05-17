<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="basic nav">
    <div class="container">
        <div class="nav-left">
            <c:choose>
                <c:when test="${not empty headerLogoText}">
                    <c:out value="${headerLogoText}"/>
                </c:when>
                <c:otherwise>Logo</c:otherwise>
            </c:choose>
        </div>
        <div class="nav-right">
            <c:forEach items="${headerTopRightNavItems}" var="navItem" varStatus="loop">
                <c:choose>
                    <c:when test="${navItem.isButton}">
                        <c:if test="${not empty navItem.textBeforeButton && empty sessionScope.loggedInUser}">
                            <c:out value="${navItem.textBeforeButton}" escapeXml="false"/>
                        </c:if>
                        <a href="${pageContext.request.contextPath}/${navItem.url}"
                           class="${not empty navItem.buttonClass ? navItem.buttonClass : ''}">
                            <c:out value="${navItem.text}"/>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${navItem.url == '#'}">
                            <span style="margin-right: 15px;"><c:out value="${navItem.text}"/></span>
                        </c:if>
                        <c:if test="${navItem.url != '#'}">
                            <a class="btn-sm btn-outline-secondary"
                               href="${pageContext.request.contextPath}/${navItem.url}"
                               style="margin-right: 15px;"><c:out value="${navItem.text}"/></a>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </div>
</div>

<div class="basic nav navlist">
    <div class="container navlist">
        <div class="nav-left">
            <div class="list">
                <c:forEach items="${headerNavItems}" var="navItem">
                    <a href="${pageContext.request.contextPath}/${navItem.url}"
                       class="${navItem.targetPageCode == pageData.pageCode ? 'active' : ''}">
                        <c:out value="${navItem.text}"/>
                    </a>
                </c:forEach>
            </div>
        </div>
        <div class="nav-right">
            <c:if test="${showSearchBarInNav}">
                <div class="search-container">
                    <input type="text" class="search-input" id="navSearchInput"
                           placeholder="<c:out value='${navSearchPlaceholder}' default='在此可输入关键词进行搜索'/>">
                    <span class="search-icon" id="navSearchIcon">
                        <svg width="20" height="20" viewBox="0 0 20 20" aria-hidden="true" tabindex="-1">
                            <path d="M3.197 3.197a7.5 7.5 0 0111.417 9.649l4.097 4.097a1 1 0 010 1.414l-.354.354a1 1 0 01-1.414 0l-4.096-4.097a7.5 7.5 0 01-9.65-11.417zm1.767 1.767a5 5 0 107.072 7.072 5 5 0 00-7.072-7.072z"></path>
                        </svg>
                    </span>
                </div>
            </c:if>
        </div>
    </div>
</div>
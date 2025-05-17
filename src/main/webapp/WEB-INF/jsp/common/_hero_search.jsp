<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="basic hero">
    <div class="image-bg">
        <img src="${pageContext.request.contextPath}/${pageData.heroImageUrl}"
             alt="<c:out value='${pageData.heroImageAltText}' default='主题横幅'/>">
    </div>
    <div class="container">
        <h1><c:out value="${pageData.heroTitle}"/></h1>
        <c:if test="${not empty pageData.heroSearchPlaceholder}">
            <div class="search-container">
                <input type="text" class="search-input" id="heroSearchInput"
                       placeholder="<c:out value="${pageData.heroSearchPlaceholder}"/>">
                <span class="search-icon" id="heroSearchIcon">
                    <svg width="20" height="20" viewBox="0 0 20 20" aria-hidden="true" tabindex="-1">
                        <path d="M3.197 3.197a7.5 7.5 0 0111.417 9.649l4.097 4.097a1 1 0 010 1.414l-.354.354a1 1 0 01-1.414 0l-4.096-4.097a7.5 7.5 0 01-9.65-11.417zm1.767 1.767a5 5 0 107.072 7.072 5 5 0 00-7.072-7.072z"></path>
                    </svg>
                </span>
            </div>
        </c:if>
    </div>
</div>
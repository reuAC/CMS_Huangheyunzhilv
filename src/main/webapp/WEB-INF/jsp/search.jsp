<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <c:set var="pageSpecificCss" value="search.css" scope="request"/>
    <c:set var="pageSpecificCss2" value="main.css" scope="request"/>
    <jsp:include page="common/_head.jsp"/>
</head>
<body>
<div class="page-wrapper">
    <jsp:include page="common/_header_nav.jsp"/>

    <div class="basic page-content search-results-page">
        <div class="container">
            <header class="search-header">
                <h1 class="search-query-display">搜索结果： "<span id="searchTermDisplay"><c:out value="${searchTerm}"
                                                                                                default="未知"/></span>"
                </h1>
                <p class="search-results-count">找到 <span id="resultsCount"><c:out value="${resultsCount}"
                                                                                    default="0"/></span> 条相关结果</p>
                <form action="${pageContext.request.contextPath}/search" method="get" class="search-again-form">
                    <input type="text" name="q" placeholder="再次搜索..." value="<c:out value="${searchTerm}"/>"
                           class="search-input-again">
                    <button type="submit" class="btn-search-again">搜索</button>
                </form>
            </header>

            <div class="search-results-list">
                <c:choose>
                    <c:when test="${not empty searchResults}">
                        <c:forEach items="${searchResults}" var="result">
                            <article class="search-result-item">
                                <h2 class="result-title"><a href="${pageContext.request.contextPath}/${result.linkUrl}"><c:out
                                        value="${result.title}"/></a></h2>
                                <div class="result-meta">
                                    <c:out value="${result.metaInfo}"/>
                                </div>
                                <div class="result-snippet">
                                        ${result.snippetHtml}
                                </div>
                                <a href="${pageContext.request.contextPath}/${result.linkUrl}" class="result-url"><c:out
                                        value="${result.displayUrlFragment}"/></a>
                            </article>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="no-results-found">
                            <p>抱歉，没有找到与 "<span id="searchTermNotFound"><c:out value="${searchTerm}"/></span>"相关的结果。
                            </p>

                            <ul>
                                <li>检查您的拼写是否正确。</li>
                                <li>尝试使用不同的关键词。</li>
                                <li>尝试使用更宽泛的关键词。</li>
                            </ul>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>

            <c:if test="${not empty paginationData && paginationData.totalPages > 1}">
                <nav class="pagination" aria-label="搜索结果分页">
                    <ul>
                        <li>
                            <a href="${pageContext.request.contextPath}/search?q=${searchTerm}&page=${paginationData.currentPage - 1}"
                               class="page-link prev ${paginationData.currentPage == 1 ? 'disabled' : ''}"
                               aria-disabled="${paginationData.currentPage == 1}">上一页</a>
                        </li>

                        <c:forEach begin="${paginationData.startPage}" end="${paginationData.endPage}" var="i">
                            <li>
                                <a href="${pageContext.request.contextPath}/search?q=${searchTerm}&page=${i}"
                                   class="page-link ${i == paginationData.currentPage ? 'current' : ''}"
                                   <c:if test="${i == paginationData.currentPage}">aria-current="page"</c:if>>
                                    <c:out value="${i}"/>
                                </a>
                            </li>
                        </c:forEach>

                        <li>
                            <a href="${pageContext.request.contextPath}/search?q=${searchTerm}&page=${paginationData.currentPage + 1}"
                               class="page-link next ${paginationData.currentPage == paginationData.totalPages ? 'disabled' : ''}"
                               aria-disabled="${paginationData.currentPage == paginationData.totalPages}">下一页</a>
                        </li>
                    </ul>
                </nav>
            </c:if>
        </div>
    </div>
</div>
<jsp:include page="common/_footer.jsp"/>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const urlParams = new URLSearchParams(window.location.search);
        const queryFromUrl = urlParams.get('q');

        const searchTermDisplay = document.getElementById('searchTermDisplay');
        const searchInputAgain = document.querySelector('.search-input-again');
        const searchTermNotFound = document.getElementById('searchTermNotFound');
        if (searchTermDisplay && queryFromUrl && searchTermDisplay.textContent.trim() === '未知') {
            searchTermDisplay.textContent = queryFromUrl;
        }
        if (searchInputAgain && queryFromUrl && searchInputAgain.value.trim() === '') {
            searchInputAgain.value = queryFromUrl;
        }
        if (searchTermNotFound && queryFromUrl && searchTermNotFound.textContent.trim() === '') {
            searchTermNotFound.textContent = queryFromUrl;
        }
    });
</script>
</body>
</html>
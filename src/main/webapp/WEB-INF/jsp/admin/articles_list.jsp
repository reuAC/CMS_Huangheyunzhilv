<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <c:set var="pageTitleSeoOverride" value="文章管理 - 黄河云之旅" scope="request"/>
    <c:set var="pageSpecificCss" value="admin_styles.css" scope="request"/>
    <jsp:include page="../common/_head.jsp"/>
</head>
<body>
<div class="page-wrapper">
    <jsp:include page="../common/_header_nav.jsp"/>

    <div class="admin-container">
        <h1>文章管理</h1>
        <a href="${pageContext.request.contextPath}/admin/articles/new" class="admin-button-new">新建文章</a>

        <c:if test="${not empty sessionScope.successMessage}">
            <p class="admin-message success"><c:out value="${sessionScope.successMessage}"/></p>
            <c:remove var="successMessage" scope="session"/>
        </c:if>
        <c:if test="${not empty sessionScope.errorMessage}">
            <p class="admin-message error"><c:out value="${sessionScope.errorMessage}"/></p>
            <c:remove var="errorMessage" scope="session"/>
        </c:if>
        <c:if test="${not empty requestScope.errorMessage}">
            <p class="admin-message error"><c:out value="${requestScope.errorMessage}"/></p>
        </c:if>


        <c:choose>
            <c:when test="${not empty articles}">
                <table class="admin-table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>标题</th>
                        <th>路径(Slug)</th>
                        <th>作者</th>
                        <th>状态</th>
                        <th>发布日期</th>
                        <th>更新时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${articles}" var="article">
                        <tr>
                            <td><c:out value="${article.articleId}"/></td>
                            <td><c:out value="${article.title}"/></td>
                            <td><c:out value="${article.articleSlug}"/></td>
                            <td><c:out value="${article.author}"/></td>
                            <td><c:out value="${article.status}"/></td>
                            <td><fmt:formatDate value="${article.publishDate}" pattern="yyyy-MM-dd"/></td>
                            <td><fmt:formatDate value="${article.updatedAt}" pattern="yyyy-MM-dd HH:mm"/></td>
                            <td class="actions">
                                <a href="${pageContext.request.contextPath}/admin/articles/edit?id=${article.articleId}"
                                   class="edit-link">编辑</a>
                                <a href="${pageContext.request.contextPath}/admin/articles/delete?id=${article.articleId}"
                                   class="delete-link"
                                   onclick="return confirm('确定要删除文章标题为 “<c:out
                                           value="${article.title}"/>” 的文章吗？此操作不可恢复。');">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <c:if test="${not empty pagination && pagination.totalPages > 1}">
                    <nav class="admin-pagination">
                        <c:if test="${pagination.currentPage > 1}">
                            <a href="${pageContext.request.contextPath}/admin/articles/list?page=${pagination.currentPage - 1}">上一页</a>
                        </c:if>
                        <c:if test="${pagination.currentPage <= 1}">
                            <span class="disabled">上一页</span>
                        </c:if>

                        <c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="i">
                            <c:choose>
                                <c:when test="${i == pagination.currentPage}">
                                    <span class="current"><c:out value="${i}"/></span>
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/admin/articles/list?page=${i}"><c:out
                                            value="${i}"/></a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <c:if test="${pagination.currentPage < pagination.totalPages}">
                            <a href="${pageContext.request.contextPath}/admin/articles/list?page=${pagination.currentPage + 1}">下一页</a>
                        </c:if>
                        <c:if test="${pagination.currentPage >= pagination.totalPages}">
                            <span class="disabled">下一页</span>
                        </c:if>
                        <span> (共 <c:out value="${pagination.totalPages}"/> 页 / <c:out
                                value="${pagination.totalResults}"/> 条)</span>
                    </nav>
                </c:if>

            </c:when>
            <c:otherwise>
                <p>目前还没有文章。</p>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<jsp:include page="../common/_footer.jsp"/>
<style> .admin-button-new {
    display: inline-block;
    margin-bottom: 15px;
    padding: 8px 12px;
    background-color: #28a745;
    color: white;
    text-decoration: none;
    border-radius: 4px;
} </style>
</body>
</html>
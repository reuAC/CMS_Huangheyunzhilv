<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <c:set var="pageTitleSeoOverride" value="页面元数据管理 - 黄河云之旅" scope="request"/>
    <c:set var="pageSpecificCss" value="admin_styles.css" scope="request"/>
    <jsp:include page="../common/_head.jsp"/>
</head>
<body>
<div class="page-wrapper">
    <jsp:include page="../common/_header_nav.jsp"/>
    <div class="admin-container">
        <h1>页面元数据管理</h1>

        <c:if test="${not empty sessionScope.successMessage}">
            <p class="admin-message success"><c:out value="${sessionScope.successMessage}"/></p>
            <c:remove var="successMessage" scope="session"/>
        </c:if>
        <c:if test="${not empty sessionScope.errorMessage}">
            <p class="admin-message error"><c:out value="${sessionScope.errorMessage}"/></p>
            <c:remove var="errorMessage" scope="session"/>
        </c:if>

        <c:choose>
            <c:when test="${not empty pages}">
                <table class="admin-table">
                    <thead>
                    <tr>
                        <th>页面ID</th>
                        <th>页面代码</th>
                        <th>SEO标题</th>
                        <th>Hero主标题</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pages}" var="page">
                        <tr>
                            <td><c:out value="${page.pageId}"/></td>
                            <td><c:out value="${page.pageCode}"/></td>
                            <td><c:out value="${page.pageTitleSeo}"/></td>
                            <td><c:out value="${page.heroTitle}"/></td>
                            <td class="actions">
                                <a href="${pageContext.request.contextPath}/admin/pagesmeta/edit?page_code=${page.pageCode}"
                                   class="edit-link">编辑</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>没有找到可管理的页面元数据。</p>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<jsp:include page="../common/_footer.jsp"/>
</body>
</html>
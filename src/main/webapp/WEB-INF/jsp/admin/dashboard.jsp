<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <c:set var="pageTitleSeoOverride" value="管理后台 - 黄河云之旅" scope="request"/>
    <c:set var="pageSpecificCss" value="admin_styles.css" scope="request"/>
    <jsp:include page="../common/_head.jsp"/>
</head>
<body>
<div class="page-wrapper">
    <jsp:include page="../common/_header_nav.jsp"/>

    <div class="admin-container">
        <h1>管理后台仪表盘</h1>
        <p>欢迎回来, <c:out value="${loggedInUser.username}"/>!</p>

        <nav class="admin-main-nav">
            <ul>
                <li><a href="${pageContext.request.contextPath}/admin/articles/list">文章管理</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/pagesmeta/list">页面元数据管理</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/cards/list">卡片管理</a></li>
            </ul>
        </nav>
    </div>
</div>
<jsp:include page="../common/_footer.jsp"/>
<script src="${pageContext.request.contextPath}/js/search_handler.js" defer></script>
</body>
</html>
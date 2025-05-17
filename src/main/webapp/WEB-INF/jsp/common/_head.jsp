<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><c:out value="${not empty pageTitleSeoOverride ? pageTitleSeoOverride : pageData.pageTitleSeo}"
              default="黄河云之旅"/></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
<c:if test="${not empty pageSpecificCss}">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/${pageSpecificCss}">
</c:if>
<c:if test="${not empty pageSpecificCss2}">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/${pageSpecificCss2}">
</c:if>
<c:if test="${pageData.pageCode == 'hongseyou'}">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</c:if>
<script>
    window.APP_CONTEXT_PATH = "${pageContext.request.contextPath}";
</script>
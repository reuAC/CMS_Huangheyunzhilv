<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <c:set var="pageSpecificCss" value="_form_styles.css" scope="request"/>
    <jsp:include page="common/_head.jsp"/>
    <title>用户登录 - 黄河云之旅</title>
</head>
<body>
<div class="page-wrapper">
    <jsp:include page="common/_header_nav.jsp"/>

    <div class="form-page-container">
        <h1>用户登录</h1>

        <c:if test="${not empty errorMessage}">
            <p class="message error-message"><c:out value="${errorMessage}"/></p>
        </c:if>
        <c:if test="${not empty successMessage}">
            <p class="message success-message"><c:out value="${successMessage}"/></p>
        </c:if>
        <c:if test="${not empty infoMessage}">
            <p class="message info-message"><c:out value="${infoMessage}"/></p>
        </c:if>

        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-group">
                <label for="username">用户名 <span style="color:red;">*</span></label>
                <input type="text" id="username" name="username" value="<c:out value='${usernameValue}'/>" required>
            </div>
            <div class="form-group">
                <label for="password">密码 <span style="color:red;">*</span></label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group captcha-group">
                <label for="captcha" style="flex-basis: 100%; margin-bottom: 8px;">验证码 <span
                        style="color:red;">*</span></label>
                <input type="text" id="captcha" name="captcha" required style="flex-grow:1;">
                <img id="captchaImage" src="${pageContext.request.contextPath}/captcha" alt="验证码"
                     title="点击刷新验证码">
            </div>
            <button type="submit" class="btn-submit">登录</button>
        </form>
        <div class="form-link">
            <p>还没有账户？ <a href="${pageContext.request.contextPath}/register">立即注册</a></p>
        </div>
    </div>
</div>
<jsp:include page="common/_footer.jsp"/>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        const captchaImage = document.getElementById('captchaImage');
        if (captchaImage) {
            captchaImage.addEventListener('click', function () {
                this.src = '${pageContext.request.contextPath}/captcha?r=' + Math.random();
            });
        }
    });
</script>
</body>
</html>
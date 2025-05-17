<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <c:set var="pageTitleSeoOverride" value="${pageTitle} - 黄河云之旅" scope="request"/>
    <c:set var="pageSpecificCss" value="admin_styles.css" scope="request"/>
    <jsp:include page="../common/_head.jsp"/>
    <style>
        .admin-form fieldset {
            border: 1px solid #ddd;
            padding: 15px;
            margin-bottom: 20px;
            border-radius: 4px;
        }

        .admin-form legend {
            font-weight: bold;
            padding: 0 10px;
            color: #333;
        }
    </style>
</head>
<body>
<div class="page-wrapper">
    <jsp:include page="../common/_header_nav.jsp"/>
    <div class="admin-container">
        <h1><c:out value="${pageTitle}"/></h1>

        <c:if test="${not empty requestScope.errorMessage}">
            <p class="admin-message error"><c:out value="${requestScope.errorMessage}"/></p>
        </c:if>
        <c:if test="${not empty sessionScope.errorMessage}">
            <p class="admin-message error"><c:out value="${sessionScope.errorMessage}"/></p>
            <c:remove var="errorMessage" scope="session"/>
        </c:if>

        <form class="admin-form" action="${pageContext.request.contextPath}/admin/pagesmeta/save" method="post">
            <input type="hidden" name="pageId" value="<c:out value='${pageData.pageId}'/>">
            <input type="hidden" name="pageCode" value="<c:out value='${pageData.pageCode}'/>">

            <fieldset>
                <legend>页面基本信息 (<c:out value="${pageData.pageCode}"/>)</legend>
                <div class="form-group">
                    <label for="pageTitleSeo">SEO页面标题</label>
                    <input type="text" id="pageTitleSeo" name="pageTitleSeo"
                           value="<c:out value='${pageData.pageTitleSeo}'/>">
                </div>
                <div class="form-group">
                    <label for="heroTitle">Hero区主标题</label>
                    <input type="text" id="heroTitle" name="heroTitle" value="<c:out value='${pageData.heroTitle}'/>">
                </div>
                <div class="form-group">
                    <label for="heroSubtitle">Hero区副标题</label>
                    <textarea id="heroSubtitle" name="heroSubtitle" rows="3"><c:out
                            value='${pageData.heroSubtitle}'/></textarea>
                </div>
                <div class="form-group">
                    <label for="heroSearchPlaceholder">Hero区搜索框占位符</label>
                    <input type="text" id="heroSearchPlaceholder" name="heroSearchPlaceholder"
                           value="<c:out value='${pageData.heroSearchPlaceholder}'/>">
                </div>
                <div class="form-group">
                    <label for="heroImageUrl">Hero区背景图片URL</label>
                    <input type="text" id="heroImageUrl" name="heroImageUrl"
                           value="<c:out value='${pageData.heroImageUrl}'/>">
                </div>
                <div class="form-group">
                    <label for="heroImageAltText">Hero区背景图片ALT文本</label>
                    <input type="text" id="heroImageAltText" name="heroImageAltText"
                           value="<c:out value='${pageData.heroImageAltText}'/>">
                </div>
            </fieldset>

            <c:if test="${not empty sections}">
                <fieldset>
                    <legend>内容版块</legend>
                    <c:forEach items="${sections}" var="section">
                        <input type="hidden" name="sectionId" value="<c:out value='${section.sectionId}'/>">
                        <h3 style="margin-top:15px; margin-bottom: 5px; font-size: 1.1em;">版块: <c:out
                                value="${section.sectionIdentifier}"/></h3>
                        <div class="form-group">
                            <label for="sectionTitle_${section.sectionId}">版块主标题</label>
                            <input type="text" id="sectionTitle_${section.sectionId}"
                                   name="sectionTitle_${section.sectionId}" value="<c:out value='${section.title}'/>">
                        </div>
                        <div class="form-group">
                            <label for="sectionSubtitle_${section.sectionId}">版块副标题</label>
                            <textarea id="sectionSubtitle_${section.sectionId}"
                                      name="sectionSubtitle_${section.sectionId}" rows="2"><c:out
                                    value='${section.subtitle}'/></textarea>
                        </div>
                        <hr style="margin: 15px 0; border-top: 1px solid #eee;"/>
                    </c:forEach>
                </fieldset>
            </c:if>

            <button type="submit" class="btn-submit">保存更改</button>
            <a href="${pageContext.request.contextPath}/admin/pagesmeta/list"
               style="display:inline-block; margin-left:10px; padding: 8px 12px; background-color: #6c757d; color:white; text-decoration:none; border-radius:4px;">取消</a>
        </form>
    </div>
</div>
<jsp:include page="../common/_footer.jsp"/>
</body>
</html>
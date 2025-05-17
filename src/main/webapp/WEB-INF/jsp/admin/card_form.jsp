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

        <form class="admin-form" action="${pageContext.request.contextPath}/admin/cards/save" method="post">
            <input type="hidden" name="cardId" value="<c:out value='${card.cardId}'/>">

            <fieldset>
                <legend>基本信息</legend>
                <div class="form-group">
                    <label for="pageCode">页面代码 (只读)</label>
                    <input type="text" id="pageCode" name="pageCode" value="<c:out value='${card.pageCode}'/>" readonly>
                </div>
                <div class="form-group">
                    <label for="cardType">卡片类型 (只读)</label>
                    <input type="text" id="cardType" name="cardType" value="<c:out value='${card.cardType}'/>" readonly>
                </div>
                <div class="form-group">
                    <label for="sectionId">所属版块ID (可选, 数字)</label>
                    <input type="number" id="sectionId" name="sectionId" value="<c:out value='${card.sectionId}'/>">
                </div>
                <div class="form-group">
                    <label for="displayOrder">显示顺序 (数字)</label>
                    <input type="number" id="displayOrder" name="displayOrder"
                           value="<c:out value='${card.displayOrder}' default='0'/>" required>
                </div>
            </fieldset>

            <fieldset>
                <legend>内容信息</legend>
                <div class="form-group">
                    <label for="title">标题</label>
                    <input type="text" id="title" name="title" value="<c:out value='${card.title}'/>">
                </div>
                <div class="form-group">
                    <label for="subtitle">副标题/描述</label>
                    <textarea id="subtitle" name="subtitle" rows="3"><c:out value='${card.subtitle}'/></textarea>
                </div>
                <div class="form-group">
                    <label for="placeholderLogoText">占位符Logo文字 (例如: A, 家)</label>
                    <input type="text" id="placeholderLogoText" name="placeholderLogoText"
                           value="<c:out value='${card.placeholderLogoText}'/>" maxlength="10">
                </div>
                <div class="form-group">
                    <label for="iconSvg">图标SVG代码 (可选)</label>
                    <textarea id="iconSvg" name="iconSvg" rows="3"><c:out value='${card.iconSvg}'/></textarea>
                </div>
            </fieldset>

            <fieldset>
                <legend>图片与链接</legend>
                <div class="form-group">
                    <label for="imageUrl">图片URL (例如: images/cards/my-card.jpg)</label>
                    <input type="text" id="imageUrl" name="imageUrl" value="<c:out value='${card.imageUrl}'/>">
                </div>
                <div class="form-group">
                    <label for="imageAltText">图片ALT文本</label>
                    <input type="text" id="imageAltText" name="imageAltText"
                           value="<c:out value='${card.imageAltText}'/>">
                </div>
                <div class="form-group">
                    <label for="linkUrl">传统链接URL (备用)</label>
                    <input type="text" id="linkUrl" name="linkUrl" value="<c:out value='${card.linkUrl}'/>">
                </div>
                <div class="form-group">
                    <label for="linkText">链接文本 (例如: 了解更多)</label>
                    <input type="text" id="linkText" name="linkText" value="<c:out value='${card.linkText}'/>">
                </div>
                <div class="form-group">
                    <label for="linkedArticleSlug">关联文章Slug (例如: my-article)</label>
                    <input type="text" id="linkedArticleSlug" name="linkedArticleSlug"
                           value="<c:out value='${card.linkedArticleSlug}'/>">
                </div>
            </fieldset>

            <fieldset>
                <legend>元信息</legend>
                <div class="form-group">
                    <label for="metaInfo1">元信息1</label>
                    <input type="text" id="metaInfo1" name="metaInfo1" value="<c:out value='${card.metaInfo1}'/>">
                </div>
                <div class="form-group">
                    <label for="metaInfo2">元信息2</label>
                    <input type="text" id="metaInfo2" name="metaInfo2" value="<c:out value='${card.metaInfo2}'/>">
                </div>
            </fieldset>

            <button type="submit" class="btn-submit">保存卡片</button>
            <a href="${pageContext.request.contextPath}/admin/cards/list"
               style="display:inline-block; margin-left:10px; padding: 8px 12px; background-color: #6c757d; color:white; text-decoration:none; border-radius:4px;">取消</a>
        </form>
    </div>
</div>
<jsp:include page="../common/_footer.jsp"/>
</body>
</html>
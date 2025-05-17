<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <c:set var="pageTitleSeoOverride" value="${pageTitle} - 黄河云之旅" scope="request"/>
    <c:set var="pageSpecificCss" value="admin_styles.css" scope="request"/>
    <jsp:include page="../common/_head.jsp"/>
</head>
<body>
<div class="page-wrapper">
    <jsp:include page="../common/_header_nav.jsp"/>

    <div class="admin-container">
        <h1><c:out value="${pageTitle}"/></h1>

        <c:if test="${not empty errorMessage}">
            <p class="admin-message error"><c:out value="${errorMessage}"/></p>
        </c:if>

        <form class="admin-form" action="${pageContext.request.contextPath}/admin/articles/save" method="post">
            <c:if test="${not empty article.articleId}">
                <input type="hidden" name="articleId" value="<c:out value='${article.articleId}'/>">
            </c:if>

            <div class="form-group">
                <label for="title">标题 <span style="color:red;">*</span></label>
                <input type="text" id="title" name="title" value="<c:out value='${article.title}'/>" required>
            </div>
            <div class="form-group">
                <label for="slug">路径 (Slug) <span style="color:red;">*</span> (例如: my-first-article)</label>
                <input type="text" id="slug" name="slug" value="<c:out value='${article.articleSlug}'/>" required
                       pattern="[a-z0-9]+(?:-[a-z0-9]+)*" title="只能使用小写字母、数字和连字符(-)">
            </div>
            <div class="form-group">
                <label for="pageTitleSeo">SEO页面标题 (可选, 留空则使用文章标题)</label>
                <input type="text" id="pageTitleSeo" name="pageTitleSeo"
                       value="<c:out value='${article.pageTitleSeo}'/>">
            </div>
            <div class="form-group">
                <label for="author">作者</label>
                <input type="text" id="author" name="author"
                       value="<c:out value='${article.author}' default='${loggedInUser.username}'/>">
            </div>
            <div class="form-group">
                <label for="publishDate">发布日期 (YYYY-MM-DD)</label>
                <input type="date" id="publishDate" name="publishDate"
                       value="<fmt:formatDate value='${article.publishDate}' pattern='yyyy-MM-dd'/>">
            </div>
            <div class="form-group">
                <label for="readTime">预计阅读时间 (例如: 5 分钟)</label>
                <input type="text" id="readTime" name="readTime" value="<c:out value='${article.estimatedReadTime}'/>">
            </div>
            <div class="form-group">
                <label for="featuredImage">特色图片URL (例如: images/articles/my-image.jpg)</label>
                <input type="url" id="featuredImage" name="featuredImage"
                       value="<c:out value='${article.featuredImageUrl}'/>">
            </div>
            <div class="form-group">
                <label for="leadParagraph">引导段落/摘要</label>
                <textarea id="leadParagraph" name="leadParagraph" rows="3"><c:out
                        value='${article.leadParagraph}'/></textarea>
            </div>
            <div class="form-group">
                <label for="content">正文内容 <span style="color:red;">*</span></label>
                <textarea id="content" name="content" required rows="15"><c:out
                        value='${plainTextContentForTextarea}'/></textarea>
            </div>
            <div class="form-group">
                <label for="status">状态 <span style="color:red;">*</span></label>
                <select id="status" name="status" required>
                    <option value="published" ${article.status == 'published' ? 'selected' : ''}>已发布</option>
                    <option value="draft" ${article.status == 'draft' ? 'selected' : ''}>草稿</option>
                </select>
            </div>

            <button type="submit" class="btn-submit">${not empty article.articleId ? '更新文章' : '创建文章'}</button>
            <a href="${pageContext.request.contextPath}/admin/articles/list"
               style="display:inline-block; margin-left:10px; padding: 8px 12px; background-color: #6c757d; color:white; text-decoration:none; border-radius:4px;">取消</a>
        </form>
    </div>
</div>
<jsp:include page="../common/_footer.jsp"/>
<script>
    const slugInput = document.getElementById('slug');
    if (slugInput) {
        slugInput.addEventListener('input', function (e) {
            let value = e.target.value.toLowerCase();
            value = value.replace(/\s+/g, '-');
            value = value.replace(/-+/g, '-');
            value = value.replace(/[^a-z0-9-]/g, '');
            value = value.replace(/^-+|-+$/g, '');
            e.target.value = value;
        });
    }
</script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <c:set var="pageTitleSeoOverride" value="卡片管理 - 黄河云之旅" scope="request"/>
    <c:set var="pageSpecificCss" value="admin_styles.css" scope="request"/>
    <jsp:include page="../common/_head.jsp"/>
    <style>
        .filter-form {
            margin-bottom: 20px;
            padding: 15px;
            background-color: #f9f9f9;
            border-radius: 4px;
            display: flex;
            gap: 15px;
            align-items: center;
            flex-wrap: wrap;
        }

        .filter-form label {
            margin-right: 5px;
            font-weight: normal;
        }

        .filter-form select, .filter-form input[type="submit"], .filter-form a {
            padding: 6px 10px;
            border-radius: 3px;
            border: 1px solid #ccc;
        }

        .filter-form input[type="submit"] {
            background-color: #007bc7;
            color: white;
            cursor: pointer;
        }

        .filter-form a {
            background-color: #6c757d;
            color: white;
            text-decoration: none;
        }

        .admin-table img.thumbnail {
            max-width: 80px;
            max-height: 50px;
            vertical-align: middle;
            margin-right: 5px;
            border: 1px solid #eee;
        }
    </style>
</head>
<body>
<div class="page-wrapper">
    <jsp:include page="../common/_header_nav.jsp"/>
    <div class="admin-container">
        <h1>卡片管理</h1>

        <c:if test="${not empty sessionScope.successMessage}">
            <p class="admin-message success"><c:out value="${sessionScope.successMessage}"/></p>
            <c:remove var="successMessage" scope="session"/>
        </c:if>
        <c:if test="${not empty sessionScope.errorMessage}">
            <p class="admin-message error"><c:out value="${sessionScope.errorMessage}"/></p>
            <c:remove var="errorMessage" scope="session"/>
        </c:if>

        <form action="${pageContext.request.contextPath}/admin/cards/list" method="get" class="filter-form">
            <div>
                <label for="filterPageCode">页面代码:</label>
                <select id="filterPageCode" name="filterPageCode">
                    <option value="">所有页面</option>
                    <c:forEach items="${allPageCodes}" var="pc">
                        <option value="<c:out value='${pc}'/>" ${currentFilterPageCode == pc ? 'selected' : ''}><c:out
                                value="${pc}"/></option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label for="filterCardType">卡片类型:</label>
                <select id="filterCardType" name="filterCardType">
                    <option value="">所有类型</option>
                    <c:forEach items="${allCardTypes}" var="ct">
                        <option value="<c:out value='${ct}'/>" ${currentFilterCardType == ct ? 'selected' : ''}><c:out
                                value="${ct}"/></option>
                    </c:forEach>
                </select>
            </div>
            <input type="submit" value="筛选">
            <a href="${pageContext.request.contextPath}/admin/cards/list">重置筛选</a>
        </form>

        <c:choose>
            <c:when test="${not empty cards}">
                <table class="admin-table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>页面代码</th>
                        <th>类型</th>
                        <th>标题</th>
                        <th>图片/占位符</th>
                        <th>关联Slug</th>
                        <th>顺序</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${cards}" var="card">
                        <tr>
                            <td><c:out value="${card.cardId}"/></td>
                            <td><c:out value="${card.pageCode}"/></td>
                            <td><c:out value="${card.cardType}"/></td>
                            <td><c:out value="${card.title}"/></td>
                            <td>
                                <c:if test="${not empty card.imageUrl}">
                                    <img src="${pageContext.request.contextPath}/${card.imageUrl}"
                                         alt="<c:out value='${card.imageAltText}'/>" class="thumbnail">
                                </c:if>
                                <c:if test="${empty card.imageUrl && not empty card.placeholderLogoText}">
                                    <span style="font-size:1.5em; color:#ccc;"><c:out
                                            value="${card.placeholderLogoText}"/></span>
                                </c:if>
                            </td>
                            <td><c:out value="${card.linkedArticleSlug}"/></td>
                            <td><c:out value="${card.displayOrder}"/></td>
                            <td class="actions">
                                <a href="${pageContext.request.contextPath}/admin/cards/edit?id=${card.cardId}"
                                   class="edit-link">编辑</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <c:if test="${not empty pagination && pagination.totalPages > 1}">
                    <nav class="admin-pagination">
                        <c:if test="${pagination.currentPage > 1}">
                            <a href="${pageContext.request.contextPath}/admin/cards/list?page=${pagination.currentPage - 1}&filterPageCode=${currentFilterPageCode}&filterCardType=${currentFilterCardType}">上一页</a>
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
                                    <a href="${pageContext.request.contextPath}/admin/cards/list?page=${i}&filterPageCode=${currentFilterPageCode}&filterCardType=${currentFilterCardType}"><c:out
                                            value="${i}"/></a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:if test="${pagination.currentPage < pagination.totalPages}">
                            <a href="${pageContext.request.contextPath}/admin/cards/list?page=${pagination.currentPage + 1}&filterPageCode=${currentFilterPageCode}&filterCardType=${currentFilterCardType}">下一页</a>
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
                <p>没有找到符合条件的卡片。</p>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<jsp:include page="../common/_footer.jsp"/>
</body>
</html>
package org.reuac.yrt_001.controller.admin;

import org.reuac.yrt_001.controller.BaseServlet;
import org.reuac.yrt_001.model.Article;
import org.reuac.yrt_001.model.PaginationData;
import org.reuac.yrt_001.model.User;
import org.reuac.yrt_001.service.ArticleService;
import org.reuac.yrt_001.service.impl.ArticleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@WebServlet("/admin/articles/*")
public class AdminArticlesServlet extends BaseServlet {
    private static final int DEFAULT_PAGE_SIZE = 10;
    private ArticleService articleService;

    @Override
    public void init() throws ServletException {
        super.init();
        articleService = new ArticleServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loadCommonData(request, "admin_articles");
        String action = request.getPathInfo();
        if (action == null) {
            action = "/list";
        }

        clearSessionMessages(request.getSession());

        switch (action) {
            case "/new":
                showNewArticleForm(request, response);
                break;
            case "/edit":
                showEditArticleForm(request, response);
                break;
            case "/delete":
                deleteArticle(request, response);
                break;
            case "/list":
            default:
                listArticles(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loadCommonData(request, "admin_articles");
        String action = request.getPathInfo();
        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No action specified for POST.");
            return;
        }

        clearSessionMessages(request.getSession());

        if ("/save".equals(action)) {
            saveArticle(request, response);
        } else if ("/delete".equals(action)) {
            deleteArticle(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action for POST: " + action);
        }
    }

    private void clearSessionMessages(HttpSession session) {
        if (session != null) {
            session.removeAttribute("successMessage");
            session.removeAttribute("errorMessage");
        }
    }

    private String htmlToPlainTextForTextarea(String html) {
        if (html == null || html.isEmpty()) {
            return "";
        }
        String text = html;
        text = text.replaceAll("</p>\\s*<p>", "\n");
        text = text.replaceAll("<p>", "");
        text = text.replaceAll("</p>", "");
        text = text.replaceAll("<br\\s*/?>", "\n");
        text = text.replaceAll("<[^>]+>", "");
        return text.trim();
    }

    private String plainTextToHtmlForStorage(String plainText) {
        if (plainText == null || plainText.trim().isEmpty()) {
            return "";
        }
        String[] paragraphs = plainText.split("\\r?\\n");
        StringBuilder contentHtmlBuilder = new StringBuilder();
        for (String p : paragraphs) {
            String trimmedP = p.trim();
            if (!trimmedP.isEmpty()) {
                contentHtmlBuilder.append("<p>").append(trimmedP).append("</p>");
            }
        }
        return contentHtmlBuilder.toString();
    }


    private void listArticles(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int currentPage = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try {
                currentPage = Integer.parseInt(pageParam);
                if (currentPage < 1) currentPage = 1;
            } catch (NumberFormatException e) {

            }
        }

        List<Article> articles = articleService.getAllArticlesAdmin(currentPage, DEFAULT_PAGE_SIZE);
        PaginationData pagination = articleService.getAdminArticlePagination(currentPage, DEFAULT_PAGE_SIZE);

        request.setAttribute("articles", articles);
        request.setAttribute("pagination", pagination);
        request.getRequestDispatcher("/WEB-INF/jsp/admin/articles_list.jsp").forward(request, response);
    }

    private void showNewArticleForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Article newArticle = new Article();
        HttpSession session = request.getSession(false);
        if (session != null) {
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            if (loggedInUser != null) {
                newArticle.setAuthor(loggedInUser.getUsername());
            }
        }
        newArticle.setStatus("draft");

        request.setAttribute("article", newArticle);
        request.setAttribute("plainTextContentForTextarea", "");
        request.setAttribute("formAction", "save");
        request.setAttribute("pageTitle", "新建文章");
        request.getRequestDispatcher("/WEB-INF/jsp/admin/article_form.jsp").forward(request, response);
    }

    private void showEditArticleForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "文章ID未提供");
            return;
        }
        try {
            int articleId = Integer.parseInt(idParam);
            Optional<Article> articleOpt = articleService.getArticleById(articleId);
            if (articleOpt.isPresent()) {
                Article article = articleOpt.get();
                request.setAttribute("article", article);
                request.setAttribute("plainTextContentForTextarea", htmlToPlainTextForTextarea(article.getContentHtml()));
                request.setAttribute("formAction", "save");
                request.setAttribute("pageTitle", "编辑文章");
                request.getRequestDispatcher("/WEB-INF/jsp/admin/article_form.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("errorMessage", "未找到ID为 " + articleId + " 的文章");
                response.sendRedirect(request.getContextPath() + "/admin/articles/list");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "无效的文章ID格式");
        }
    }

    private void fillArticleFormForError(HttpServletRequest request, Article articleInProgress,
                                         String title, String slug, String author, String publishDateStr,
                                         String readTime, String featuredImage, String leadParagraph,
                                         String contentTextArea, String pageTitleSeo, String status,
                                         boolean isNew) {
        articleInProgress.setTitle(title);
        articleInProgress.setArticleSlug(slug);
        articleInProgress.setAuthor(author);
        articleInProgress.setEstimatedReadTime(readTime);
        articleInProgress.setFeaturedImageUrl(featuredImage);
        articleInProgress.setLeadParagraph(leadParagraph);
        articleInProgress.setPageTitleSeo(pageTitleSeo);
        articleInProgress.setStatus(status);

        request.setAttribute("article", articleInProgress);
        request.setAttribute("rawPublishDate", publishDateStr);
        request.setAttribute("plainTextContentForTextarea", contentTextArea != null ? contentTextArea : "");
        request.setAttribute("formAction", "save");
        request.setAttribute("pageTitle", isNew ? "新建文章" : "编辑文章");
    }


    private void saveArticle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("articleId");
        String title = request.getParameter("title");
        String slug = request.getParameter("slug");
        String author = request.getParameter("author");
        String publishDateStr = request.getParameter("publishDate");
        String readTime = request.getParameter("readTime");
        String featuredImage = request.getParameter("featuredImage");
        String leadParagraph = request.getParameter("leadParagraph");
        String contentTextArea = request.getParameter("content");
        String pageTitleSeo = request.getParameter("pageTitleSeo");
        String status = request.getParameter("status");

        if (title == null || title.trim().isEmpty() ||
                slug == null || slug.trim().isEmpty() ||
                contentTextArea == null || contentTextArea.trim().isEmpty() ||
                status == null || status.trim().isEmpty()) {

            Article tempArticle = new Article();
            if (idParam != null && !idParam.isEmpty()) {
                try {
                    tempArticle.setArticleId(Integer.parseInt(idParam));
                } catch (NumberFormatException e) {
                }
            }

            request.setAttribute("errorMessage", "标题、路径(Slug)、正文内容和状态为必填项。");
            fillArticleFormForError(request, tempArticle, title, slug, author, publishDateStr, readTime,
                    featuredImage, leadParagraph, contentTextArea, pageTitleSeo, status,
                    (idParam == null || idParam.isEmpty()));
            request.getRequestDispatcher("/WEB-INF/jsp/admin/article_form.jsp").forward(request, response);
            return;
        }


        Article article = new Article();
        boolean isNew = (idParam == null || idParam.isEmpty() || "0".equals(idParam));

        if (!isNew) {
            try {
                article.setArticleId(Integer.parseInt(idParam));
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "无效的文章ID。");
                fillArticleFormForError(request, article, title, slug, author, publishDateStr, readTime,
                        featuredImage, leadParagraph, contentTextArea, pageTitleSeo, status, isNew);
                request.getRequestDispatcher("/WEB-INF/jsp/admin/article_form.jsp").forward(request, response);
                return;
            }
        }

        article.setTitle(title.trim());
        article.setArticleSlug(slug.trim());
        article.setAuthor(author != null ? author.trim() : null);
        if (publishDateStr != null && !publishDateStr.isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false);
                Date parsedDate = sdf.parse(publishDateStr);
                article.setPublishDate(parsedDate);
            } catch (ParseException e) {
                request.setAttribute("errorMessage", "发布日期格式无效，请使用 YYYY-MM-DD。");
                fillArticleFormForError(request, article, title, slug, author, publishDateStr, readTime,
                        featuredImage, leadParagraph, contentTextArea, pageTitleSeo, status, isNew);
                request.getRequestDispatcher("/WEB-INF/jsp/admin/article_form.jsp").forward(request, response);
                return;
            }
        } else {
            article.setPublishDate(null);
        }
        article.setEstimatedReadTime(readTime != null ? readTime.trim() : null);
        article.setFeaturedImageUrl(featuredImage != null ? featuredImage.trim() : null);
        article.setLeadParagraph(leadParagraph != null ? leadParagraph.trim() : null);
        article.setContentHtml(plainTextToHtmlForStorage(contentTextArea));
        article.setPageTitleSeo(pageTitleSeo != null ? pageTitleSeo.trim() : null);
        article.setStatus(status);


        boolean success;
        String successMessage;
        String operationType = isNew ? "创建" : "更新";

        if (isNew) {
            if (articleService.slugExists(article.getArticleSlug(), null)) {
                request.setAttribute("errorMessage", "文章路径 (slug) '" + article.getArticleSlug() + "' 已存在，请使用其他路径。");
                fillArticleFormForError(request, article, title, slug, author, publishDateStr, readTime,
                        featuredImage, leadParagraph, contentTextArea, pageTitleSeo, status, true);
                request.getRequestDispatcher("/WEB-INF/jsp/admin/article_form.jsp").forward(request, response);
                return;
            }
            success = articleService.createArticle(article);
            successMessage = "文章已成功创建！";
        } else {
            if (articleService.slugExists(article.getArticleSlug(), article.getArticleId())) {
                request.setAttribute("errorMessage", "文章路径 (slug) '" + article.getArticleSlug() + "' 已被其他文章使用，请使用其他路径。");
                fillArticleFormForError(request, article, title, slug, author, publishDateStr, readTime,
                        featuredImage, leadParagraph, contentTextArea, pageTitleSeo, status, false);
                request.getRequestDispatcher("/WEB-INF/jsp/admin/article_form.jsp").forward(request, response);
                return;
            }
            success = articleService.updateArticle(article);
            successMessage = "文章已成功更新！";
        }

        if (success) {
            request.getSession().setAttribute("successMessage", successMessage);
            response.sendRedirect(request.getContextPath() + "/admin/articles/list");
        } else {
            request.setAttribute("errorMessage", operationType + "文章失败，可能是数据库错误或slug冲突。");
            fillArticleFormForError(request, article, title, slug, author, publishDateStr, readTime,
                    featuredImage, leadParagraph, contentTextArea, pageTitleSeo, status, isNew);
            request.getRequestDispatcher("/WEB-INF/jsp/admin/article_form.jsp").forward(request, response);
        }
    }

    private void deleteArticle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null) {
            request.getSession().setAttribute("errorMessage", "删除文章需要提供ID。");
            response.sendRedirect(request.getContextPath() + "/admin/articles/list");
            return;
        }
        try {
            int articleId = Integer.parseInt(idParam);
            boolean success = articleService.deleteArticle(articleId);
            if (success) {
                request.getSession().setAttribute("successMessage", "文章 (ID: " + articleId + ") 已成功删除。");
            } else {
                request.getSession().setAttribute("errorMessage", "删除文章 (ID: " + articleId + ") 失败。");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("errorMessage", "无效的文章ID格式。");
        }
        response.sendRedirect(request.getContextPath() + "/admin/articles/list");
    }
}
package org.reuac.yrt_001.controller;

import org.reuac.yrt_001.model.Article;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@WebServlet("/article/*")
public class ArticleServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loadCommonData(request, "article");

        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Article slug not provided.");
            return;
        }
        String slug = pathInfo.substring(1);

        Optional<Article> articleOpt = pageService.getArticleBySlug(slug);
        if (!articleOpt.isPresent()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Article with slug '" + slug + "' not found.");
            return;
        }
        Article article = articleOpt.get();
        request.setAttribute("article", article);
        request.setAttribute("articleTags", article.getTags());
        request.setAttribute("pageTitleSeoOverride", article.getPageTitleSeo());

        request.getRequestDispatcher("/WEB-INF/jsp/article.jsp").forward(request, response);
    }
}
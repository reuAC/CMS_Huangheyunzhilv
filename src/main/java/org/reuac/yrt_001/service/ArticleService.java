package org.reuac.yrt_001.service;

import org.reuac.yrt_001.model.Article;
import org.reuac.yrt_001.model.PaginationData;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    List<Article> getAllArticlesAdmin(int pageNumber, int pageSize);

    PaginationData getAdminArticlePagination(int pageNumber, int pageSize);

    Optional<Article> getArticleById(int articleId);

    boolean createArticle(Article article);

    boolean updateArticle(Article article);

    boolean deleteArticle(int articleId);

    boolean slugExists(String slug, Integer excludeArticleId);
}
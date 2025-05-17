package org.reuac.yrt_001.service.impl;

import org.reuac.yrt_001.dao.ArticleDAO;
import org.reuac.yrt_001.dao.impl.ArticleDAOImpl;
import org.reuac.yrt_001.model.Article;
import org.reuac.yrt_001.model.PaginationData;
import org.reuac.yrt_001.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ArticleServiceImpl implements ArticleService {
    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
    private final ArticleDAO articleDAO = new ArticleDAOImpl();

    @Override
    public List<Article> getAllArticlesAdmin(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return articleDAO.findAllAdmin(pageSize, offset);
    }

    @Override
    public PaginationData getAdminArticlePagination(int pageNumber, int pageSize) {
        int totalResults = articleDAO.countAllAdmin();
        int totalPages = (int) Math.ceil((double) totalResults / pageSize);
        return new PaginationData(pageNumber, totalPages, totalResults);
    }

    @Override
    public Optional<Article> getArticleById(int articleId) {
        return articleDAO.findById(articleId);
    }

    @Override
    public boolean createArticle(Article article) {
        if (slugExists(article.getArticleSlug(), null)) {
            logger.warn("Attempt to create article with existing slug: {}", article.getArticleSlug());
            return false;
        }

        return articleDAO.save(article);
    }

    @Override
    public boolean updateArticle(Article article) {
        if (slugExists(article.getArticleSlug(), article.getArticleId())) {
            logger.warn("Attempt to update article ID {} with a slug {} that already exists for another article.", article.getArticleId(), article.getArticleSlug());
            return false;
        }
        return articleDAO.update(article);
    }

    @Override
    public boolean deleteArticle(int articleId) {
        return articleDAO.deleteById(articleId);
    }

    @Override
    public boolean slugExists(String slug, Integer excludeArticleId) {
        if (slug == null || slug.trim().isEmpty()) {


            return false;
        }
        Optional<Article> existingArticleOpt = articleDAO.findBySlug(slug.trim());

        if (existingArticleOpt.isPresent()) {
            Article existingArticle = existingArticleOpt.get();
            if (excludeArticleId == null) {


                return true;
            } else {


                return !Objects.equals(existingArticle.getArticleId(), excludeArticleId);
            }
        }

        return false;
    }
}
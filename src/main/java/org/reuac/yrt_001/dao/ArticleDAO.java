package org.reuac.yrt_001.dao;

import org.reuac.yrt_001.model.Article;
import org.reuac.yrt_001.model.SearchResult;

import java.util.List;
import java.util.Optional;

public interface ArticleDAO {
    Optional<Article> findBySlug(String slug);

    Optional<Article> findById(int articleId);

    List<Article> findAllPublished(int limit, int offset);

    List<Article> findAllAdmin(int limit, int offset);

    int countAllPublished();

    int countAllAdmin();

    List<SearchResult> searchArticles(String searchTerm, int limit, int offset);

    int countSearchResults(String searchTerm);

    boolean save(Article article);

    boolean update(Article article);

    boolean deleteById(int articleId);
}
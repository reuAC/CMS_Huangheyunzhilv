package org.reuac.yrt_001.dao.impl;

import org.reuac.yrt_001.config.DataSourceConfig;
import org.reuac.yrt_001.dao.ArticleDAO;
import org.reuac.yrt_001.dao.TagDAO;
import org.reuac.yrt_001.model.Article;
import org.reuac.yrt_001.model.SearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArticleDAOImpl implements ArticleDAO {
    private static final Logger logger = LoggerFactory.getLogger(ArticleDAOImpl.class);
    private final TagDAO tagDAO = new TagDAOImpl();

    private Article mapRowToArticle(ResultSet rs) throws SQLException {
        Article article = new Article();
        article.setArticleId(rs.getInt("article_id"));
        article.setArticleSlug(rs.getString("article_slug"));
        article.setTitle(rs.getString("title"));
        article.setAuthor(rs.getString("author"));
        article.setPublishDate(rs.getDate("publish_date"));
        article.setEstimatedReadTime(rs.getString("estimated_read_time"));
        article.setFeaturedImageUrl(rs.getString("featured_image_url"));
        article.setLeadParagraph(rs.getString("lead_paragraph"));
        article.setContentHtml(rs.getString("content_html"));
        article.setPageTitleSeo(rs.getString("page_title_seo"));
        article.setStatus(rs.getString("status"));
        article.setCreatedAt(rs.getTimestamp("created_at"));
        article.setUpdatedAt(rs.getTimestamp("updated_at"));
        return article;
    }

    @Override
    public Optional<Article> findById(int articleId) {
        String sql = "SELECT * FROM articles WHERE article_id = ?";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, articleId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Article article = mapRowToArticle(rs);
                    article.setTags(tagDAO.findByArticleId(article.getArticleId()));
                    return Optional.of(article);
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding article by ID: " + articleId, e);
        }
        return Optional.empty();
    }

    @Override
    public List<Article> findAllAdmin(int limit, int offset) {
        List<Article> articles = new ArrayList<>();

        String sql = "SELECT * FROM articles ORDER BY updated_at DESC LIMIT ? OFFSET ?";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    articles.add(mapRowToArticle(rs));
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding all articles for admin", e);
        }
        return articles;
    }

    @Override
    public int countAllAdmin() {
        String sql = "SELECT COUNT(*) FROM articles";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("Error counting all articles for admin", e);
        }
        return 0;
    }

    @Override
    public boolean save(Article article) {

        String sql = "INSERT INTO articles (article_slug, title, author, publish_date, estimated_read_time, " +
                "featured_image_url, lead_paragraph, content_html, page_title_seo, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, article.getArticleSlug());
            ps.setString(2, article.getTitle());
            ps.setString(3, article.getAuthor());
            ps.setDate(4, article.getPublishDate() != null ? new java.sql.Date(article.getPublishDate().getTime()) : null);
            ps.setString(5, article.getEstimatedReadTime());
            ps.setString(6, article.getFeaturedImageUrl());
            ps.setString(7, article.getLeadParagraph());
            ps.setString(8, article.getContentHtml());
            ps.setString(9, article.getPageTitleSeo());
            ps.setString(10, article.getStatus());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        article.setArticleId(generatedKeys.getInt(1));


                    }
                }
                return true;
            }
        } catch (SQLException e) {
            logger.error("Error saving article: " + article.getTitle(), e);
        }
        return false;
    }

    @Override
    public boolean update(Article article) {

        String sql = "UPDATE articles SET article_slug = ?, title = ?, author = ?, publish_date = ?, " +
                "estimated_read_time = ?, featured_image_url = ?, lead_paragraph = ?, content_html = ?, " +
                "page_title_seo = ?, status = ?, updated_at = CURRENT_TIMESTAMP WHERE article_id = ?";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, article.getArticleSlug());
            ps.setString(2, article.getTitle());
            ps.setString(3, article.getAuthor());
            ps.setDate(4, article.getPublishDate() != null ? new java.sql.Date(article.getPublishDate().getTime()) : null);
            ps.setString(5, article.getEstimatedReadTime());
            ps.setString(6, article.getFeaturedImageUrl());
            ps.setString(7, article.getLeadParagraph());
            ps.setString(8, article.getContentHtml());
            ps.setString(9, article.getPageTitleSeo());
            ps.setString(10, article.getStatus());
            ps.setInt(11, article.getArticleId());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {


                return true;
            }
        } catch (SQLException e) {
            logger.error("Error updating article: " + article.getTitle(), e);
        }
        return false;
    }


    @Override
    public boolean deleteById(int articleId) {

        String deleteTagsSql = "DELETE FROM article_tags_map WHERE article_id = ?";
        String deleteArticleSql = "DELETE FROM articles WHERE article_id = ?";
        Connection conn = null;
        boolean success = false;
        try {
            conn = DataSourceConfig.getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement psTags = conn.prepareStatement(deleteTagsSql)) {
                psTags.setInt(1, articleId);
                psTags.executeUpdate();
            }

            try (PreparedStatement psArticle = conn.prepareStatement(deleteArticleSql)) {
                psArticle.setInt(1, articleId);
                int rowsAffected = psArticle.executeUpdate();
                if (rowsAffected > 0) {
                    success = true;
                }
            }
            conn.commit();
        } catch (SQLException e) {
            logger.error("Error deleting article by ID: " + articleId, e);
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    logger.error("Error rolling back transaction for article delete", ex);
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    logger.error("Error closing connection after article delete", e);
                }
            }
        }
        return success;
    }

    @Override
    public Optional<Article> findBySlug(String slug) {
        String sql = "SELECT * FROM articles WHERE article_slug = ? AND status = 'published'";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, slug);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Article article = mapRowToArticle(rs);
                    article.setTags(tagDAO.findByArticleId(article.getArticleId()));
                    return Optional.of(article);
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding article by slug: " + slug, e);
        }
        return Optional.empty();
    }

    @Override
    public List<Article> findAllPublished(int limit, int offset) {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM articles WHERE status = 'published' ORDER BY publish_date DESC LIMIT ? OFFSET ?";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Article article = mapRowToArticle(rs);


                    articles.add(article);
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding all published articles", e);
        }
        return articles;
    }

    @Override
    public int countAllPublished() {
        String sql = "SELECT COUNT(*) FROM articles WHERE status = 'published'";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("Error counting all published articles", e);
        }
        return 0;
    }


    @Override
    public List<SearchResult> searchArticles(String searchTerm, int limit, int offset) {
        List<SearchResult> results = new ArrayList<>();
        String query = "%" + searchTerm + "%";

        String sql = "SELECT article_id, article_slug, title, author, publish_date, lead_paragraph " +
                "FROM articles " +
                "WHERE status = 'published' AND (title LIKE ? OR lead_paragraph LIKE ?) " +
                "ORDER BY publish_date DESC LIMIT ? OFFSET ?";

        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, query);
            ps.setString(2, query);
            ps.setInt(3, limit);
            ps.setInt(4, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String title = rs.getString("title");
                    String lead = rs.getString("lead_paragraph");
                    String slug = rs.getString("article_slug");

                    String snippet = lead.length() > 150 ? lead.substring(0, 150) + "..." : lead;

                    snippet = snippet.replaceAll("(?i)" + searchTerm, "<strong>$0</strong>");

                    String metaInfo = "作者：" + rs.getString("author") + " | 发布于：" +
                            new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("publish_date"));

                    String linkUrl = "article/" + slug;
                    String displayUrlFragment = "/articles/" + slug + ".html";

                    results.add(new SearchResult(title, metaInfo, snippet, linkUrl, displayUrlFragment));
                }
            }
        } catch (SQLException e) {
            logger.error("Error searching articles for term: " + searchTerm, e);
        }
        return results;
    }

    @Override
    public int countSearchResults(String searchTerm) {
        String query = "%" + searchTerm + "%";
        String sql = "SELECT COUNT(*) FROM articles WHERE status = 'published' AND (title LIKE ? OR lead_paragraph LIKE ?)";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, query);
            ps.setString(2, query);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            logger.error("Error counting search results for term: " + searchTerm, e);
        }
        return 0;
    }
}
package org.reuac.yrt_001.dao.impl;

import org.reuac.yrt_001.config.DataSourceConfig;
import org.reuac.yrt_001.dao.PageDAO;
import org.reuac.yrt_001.model.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PageDAOImpl implements PageDAO {
    private static final Logger logger = LoggerFactory.getLogger(PageDAOImpl.class);

    private Page mapRowToPage(ResultSet rs) throws SQLException {
        Page page = new Page();
        page.setPageId(rs.getInt("page_id"));
        page.setPageCode(rs.getString("page_code"));
        page.setPageTitleSeo(rs.getString("page_title_seo"));
        page.setHeroTitle(rs.getString("hero_title"));
        page.setHeroSubtitle(rs.getString("hero_subtitle"));
        page.setHeroSearchPlaceholder(rs.getString("hero_search_placeholder"));
        page.setHeroImageUrl(rs.getString("hero_image_url"));
        page.setHeroImageAltText(rs.getString("hero_image_alt_text"));
        page.setCreatedAt(rs.getTimestamp("created_at"));
        page.setUpdatedAt(rs.getTimestamp("updated_at"));
        return page;
    }

    @Override
    public Optional<Page> findByPageCode(String pageCode) {
        String sql = "SELECT * FROM pages WHERE page_code = ?";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pageCode);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRowToPage(rs));
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding page by code: " + pageCode, e);
        }
        return Optional.empty();
    }

    @Override
    public List<Page> findAll() {
        List<Page> pages = new ArrayList<>();
        String sql = "SELECT * FROM pages ORDER BY page_code ASC";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                pages.add(mapRowToPage(rs));
            }
        } catch (SQLException e) {
            logger.error("Error finding all pages", e);
        }
        return pages;
    }

    private boolean executeUpdate(Page page, Connection conn, boolean closeConnection) throws SQLException {
        String sql = "UPDATE pages SET page_title_seo = ?, hero_title = ?, hero_subtitle = ?, " +
                "hero_search_placeholder = ?, hero_image_url = ?, hero_image_alt_text = ?, " +
                "updated_at = CURRENT_TIMESTAMP WHERE page_id = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, page.getPageTitleSeo());
            ps.setString(2, page.getHeroTitle());
            ps.setString(3, page.getHeroSubtitle());
            ps.setString(4, page.getHeroSearchPlaceholder());
            ps.setString(5, page.getHeroImageUrl());
            ps.setString(6, page.getHeroImageAltText());
            ps.setInt(7, page.getPageId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (closeConnection && conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public boolean update(Page page) {
        Connection conn = null;
        try {
            conn = DataSourceConfig.getConnection();
            return executeUpdate(page, conn, true);
        } catch (SQLException e) {
            logger.error("Error updating page: " + page.getPageCode(), e);
            return false;
        }

    }

    @Override
    public boolean updateInTransaction(Page page, Connection conn) {
        try {
            return executeUpdate(page, conn, false);
        } catch (SQLException e) {
            logger.error("Error updating page in transaction: " + page.getPageCode(), e);

            throw new RuntimeException("Error updating page in transaction", e);
        }
    }
}
package org.reuac.yrt_001.dao.impl;

import org.reuac.yrt_001.config.DataSourceConfig;
import org.reuac.yrt_001.dao.ContentSectionDAO;
import org.reuac.yrt_001.model.ContentSection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContentSectionDAOImpl implements ContentSectionDAO {
    private static final Logger logger = LoggerFactory.getLogger(ContentSectionDAOImpl.class);

    private ContentSection mapRowToContentSection(ResultSet rs) throws SQLException {
        ContentSection section = new ContentSection();
        section.setSectionId(rs.getInt("section_id"));
        section.setPageCode(rs.getString("page_code"));
        section.setSectionIdentifier(rs.getString("section_identifier"));
        section.setTitle(rs.getString("title"));
        section.setSubtitle(rs.getString("subtitle"));
        section.setDisplayOrder(rs.getInt("display_order"));
        section.setCreatedAt(rs.getTimestamp("created_at"));
        section.setUpdatedAt(rs.getTimestamp("updated_at"));
        return section;
    }

    @Override
    public Optional<ContentSection> findByPageCodeAndIdentifier(String pageCode, String sectionIdentifier) {
        String sql = "SELECT * FROM content_sections WHERE page_code = ? AND section_identifier = ?";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pageCode);
            ps.setString(2, sectionIdentifier);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRowToContentSection(rs));
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding content section by pageCode '" + pageCode + "' and identifier '" + sectionIdentifier + "'", e);
        }
        return Optional.empty();
    }

    @Override
    public List<ContentSection> findByPageCode(String pageCode) {
        List<ContentSection> sections = new ArrayList<>();
        String sql = "SELECT * FROM content_sections WHERE page_code = ? ORDER BY display_order ASC";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pageCode);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    sections.add(mapRowToContentSection(rs));
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding content sections by pageCode: " + pageCode, e);
        }
        return sections;
    }

    private boolean executeUpdate(ContentSection section, Connection conn, boolean closeConnection) throws SQLException {
        String sql = "UPDATE content_sections SET title = ?, subtitle = ?, updated_at = CURRENT_TIMESTAMP " +
                "WHERE section_id = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, section.getTitle());
            ps.setString(2, section.getSubtitle());
            ps.setInt(3, section.getSectionId());
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
    public boolean update(ContentSection section) {
        Connection conn = null;
        try {
            conn = DataSourceConfig.getConnection();
            return executeUpdate(section, conn, true);
        } catch (SQLException e) {
            logger.error("Error updating content section: " + section.getSectionIdentifier(), e);
            return false;
        }
    }

    @Override
    public boolean updateInTransaction(ContentSection section, Connection conn) {
        try {
            return executeUpdate(section, conn, false);
        } catch (SQLException e) {
            logger.error("Error updating content section in transaction: " + section.getSectionIdentifier(), e);
            throw new RuntimeException("Error updating content section in transaction", e);
        }
    }
}
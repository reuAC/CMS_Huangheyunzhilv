package org.reuac.yrt_001.dao.impl;

import org.reuac.yrt_001.config.DataSourceConfig;
import org.reuac.yrt_001.dao.TagDAO;
import org.reuac.yrt_001.model.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagDAOImpl implements TagDAO {
    private static final Logger logger = LoggerFactory.getLogger(TagDAOImpl.class);

    @Override
    public List<Tag> findByArticleId(int articleId) {
        List<Tag> tags = new ArrayList<>();
        String sql = "SELECT t.tag_id, t.tag_name, t.tag_slug, t.created_at " +
                "FROM tags t JOIN article_tags_map atm ON t.tag_id = atm.tag_id " +
                "WHERE atm.article_id = ?";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, articleId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Tag tag = new Tag();
                    tag.setTagId(rs.getInt("tag_id"));
                    tag.setTagName(rs.getString("tag_name"));
                    tag.setTagSlug(rs.getString("tag_slug"));
                    tag.setCreatedAt(rs.getTimestamp("created_at"));
                    tags.add(tag);
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding tags by articleId: " + articleId, e);
        }
        return tags;
    }
}
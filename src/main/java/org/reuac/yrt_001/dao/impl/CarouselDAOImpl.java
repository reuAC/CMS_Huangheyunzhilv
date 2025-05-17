package org.reuac.yrt_001.dao.impl;

import org.reuac.yrt_001.config.DataSourceConfig;
import org.reuac.yrt_001.dao.CarouselDAO;
import org.reuac.yrt_001.model.CarouselSlide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarouselDAOImpl implements CarouselDAO {
    private static final Logger logger = LoggerFactory.getLogger(CarouselDAOImpl.class);

    @Override
    public List<CarouselSlide> findByPageCodeAndActive(String pageCode, boolean isActive) {
        List<CarouselSlide> slides = new ArrayList<>();
        String sql = "SELECT * FROM carousel_slides WHERE page_code = ? AND is_active = ? ORDER BY display_order ASC";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pageCode);
            ps.setBoolean(2, isActive);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CarouselSlide slide = new CarouselSlide();
                    slide.setSlideId(rs.getInt("slide_id"));
                    slide.setPageCode(rs.getString("page_code"));
                    slide.setImageUrl(rs.getString("image_url"));
                    slide.setImageAltText(rs.getString("image_alt_text"));
                    slide.setCaptionTitle(rs.getString("caption_title"));
                    slide.setCaptionText(rs.getString("caption_text"));
                    slide.setLinkUrl(rs.getString("link_url"));
                    slide.setLinkedArticleSlug(rs.getString("linked_article_slug"));
                    slide.setDisplayOrder(rs.getInt("display_order"));
                    slide.setActive(rs.getBoolean("is_active"));
                    slide.setCreatedAt(rs.getTimestamp("created_at"));
                    slide.setUpdatedAt(rs.getTimestamp("updated_at"));
                    slides.add(slide);
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding carousel slides by pageCode '" + pageCode + "' and active status " + isActive, e);
        }
        return slides;
    }
}
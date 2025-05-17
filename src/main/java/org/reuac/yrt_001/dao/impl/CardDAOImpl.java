package org.reuac.yrt_001.dao.impl;

import org.reuac.yrt_001.config.DataSourceConfig;
import org.reuac.yrt_001.dao.CardDAO;
import org.reuac.yrt_001.model.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardDAOImpl implements CardDAO {
    private static final Logger logger = LoggerFactory.getLogger(CardDAOImpl.class);

    private Card mapRowToCard(ResultSet rs) throws SQLException {
        Card card = new Card();
        card.setCardId(rs.getInt("card_id"));
        int sectionIdVal = rs.getInt("section_id");
        if (rs.wasNull()) {
            card.setSectionId(null);
        } else {
            card.setSectionId(sectionIdVal);
        }
        card.setPageCode(rs.getString("page_code"));
        card.setCardType(rs.getString("card_type"));
        card.setTitle(rs.getString("title"));
        card.setSubtitle(rs.getString("subtitle"));
        card.setImageUrl(rs.getString("image_url"));
        card.setImageAltText(rs.getString("image_alt_text"));
        card.setPlaceholderLogoText(rs.getString("placeholder_logo_text"));
        card.setLinkUrl(rs.getString("link_url"));
        card.setLinkText(rs.getString("link_text"));
        card.setLinkedArticleSlug(rs.getString("linked_article_slug"));
        card.setMetaInfo1(rs.getString("meta_info_1"));
        card.setMetaInfo2(rs.getString("meta_info_2"));
        card.setDisplayOrder(rs.getInt("display_order"));
        card.setIconSvg(rs.getString("icon_svg"));
        card.setCreatedAt(rs.getTimestamp("created_at"));
        card.setUpdatedAt(rs.getTimestamp("updated_at"));
        return card;
    }

    @Override
    public List<Card> findBySectionId(int sectionId) {
        List<Card> cards = new ArrayList<>();
        String sql = "SELECT * FROM cards WHERE section_id = ? ORDER BY display_order ASC";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sectionId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cards.add(mapRowToCard(rs));
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding cards by sectionId: " + sectionId, e);
        }
        return cards;
    }

    @Override
    public List<Card> findByPageCodeAndType(String pageCode, String cardType) {
        List<Card> cards = new ArrayList<>();
        String sql = "SELECT * FROM cards WHERE page_code = ? AND card_type = ? ORDER BY display_order ASC";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pageCode);
            ps.setString(2, cardType);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cards.add(mapRowToCard(rs));
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding cards by pageCode '" + pageCode + "' and type '" + cardType + "'", e);
        }
        return cards;
    }

    @Override
    public List<Card> findByPageCode(String pageCode) {
        List<Card> cards = new ArrayList<>();
        String sql = "SELECT * FROM cards WHERE page_code = ? ORDER BY display_order ASC";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pageCode);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cards.add(mapRowToCard(rs));
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding cards by pageCode: " + pageCode, e);
        }
        return cards;
    }

    @Override
    public Optional<Card> findById(int cardId) {
        String sql = "SELECT * FROM cards WHERE card_id = ?";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cardId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRowToCard(rs));
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding card by ID: " + cardId, e);
        }
        return Optional.empty();
    }

    @Override
    public List<Card> findAllPaginated(String filterPageCode, String filterCardType, int limit, int offset) {
        List<Card> cards = new ArrayList<>();
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM cards WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        if (filterPageCode != null && !filterPageCode.isEmpty()) {
            sqlBuilder.append("AND page_code = ? ");
            params.add(filterPageCode);
        }
        if (filterCardType != null && !filterCardType.isEmpty()) {
            sqlBuilder.append("AND card_type = ? ");
            params.add(filterCardType);
        }
        sqlBuilder.append("ORDER BY page_code ASC, card_type ASC, display_order ASC LIMIT ? OFFSET ?");
        params.add(limit);
        params.add(offset);

        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlBuilder.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cards.add(mapRowToCard(rs));
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding all paginated cards with filters", e);
        }
        return cards;
    }

    @Override
    public int countAllFiltered(String filterPageCode, String filterCardType) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT COUNT(*) FROM cards WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        if (filterPageCode != null && !filterPageCode.isEmpty()) {
            sqlBuilder.append("AND page_code = ? ");
            params.add(filterPageCode);
        }
        if (filterCardType != null && !filterCardType.isEmpty()) {
            sqlBuilder.append("AND card_type = ? ");
            params.add(filterCardType);
        }

        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlBuilder.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            logger.error("Error counting all filtered cards", e);
        }
        return 0;
    }

    @Override
    public boolean update(Card card) {
        String sql = "UPDATE cards SET page_code = ?, section_id = ?, card_type = ?, title = ?, subtitle = ?, " +
                "image_url = ?, image_alt_text = ?, placeholder_logo_text = ?, link_url = ?, link_text = ?, " +
                "linked_article_slug = ?, meta_info_1 = ?, meta_info_2 = ?, icon_svg = ?, display_order = ?, " +
                "updated_at = CURRENT_TIMESTAMP WHERE card_id = ?";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, card.getPageCode());
            if (card.getSectionId() != null) {
                ps.setInt(2, card.getSectionId());
            } else {
                ps.setNull(2, Types.INTEGER);
            }
            ps.setString(3, card.getCardType());
            ps.setString(4, card.getTitle());
            ps.setString(5, card.getSubtitle());
            ps.setString(6, card.getImageUrl());
            ps.setString(7, card.getImageAltText());
            ps.setString(8, card.getPlaceholderLogoText());
            ps.setString(9, card.getLinkUrl());
            ps.setString(10, card.getLinkText());
            ps.setString(11, card.getLinkedArticleSlug());
            ps.setString(12, card.getMetaInfo1());
            ps.setString(13, card.getMetaInfo2());
            ps.setString(14, card.getIconSvg());
            ps.setInt(15, card.getDisplayOrder());
            ps.setInt(16, card.getCardId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("Error updating card with ID: " + card.getCardId(), e);
            return false;
        }
    }

    @Override
    public List<String> getAllDistinctPageCodes() {
        List<String> pageCodes = new ArrayList<>();
        String sql = "SELECT DISTINCT page_code FROM cards ORDER BY page_code ASC";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                pageCodes.add(rs.getString("page_code"));
            }
        } catch (SQLException e) {
            logger.error("Error fetching distinct page codes from cards", e);
        }
        return pageCodes;
    }

    @Override
    public List<String> getAllDistinctCardTypes() {
        List<String> cardTypes = new ArrayList<>();
        String sql = "SELECT DISTINCT card_type FROM cards ORDER BY card_type ASC";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                cardTypes.add(rs.getString("card_type"));
            }
        } catch (SQLException e) {
            logger.error("Error fetching distinct card types from cards", e);
        }
        return cardTypes;
    }
}
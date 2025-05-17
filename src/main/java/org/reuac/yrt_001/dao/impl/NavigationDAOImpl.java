package org.reuac.yrt_001.dao.impl;

import org.reuac.yrt_001.config.DataSourceConfig;
import org.reuac.yrt_001.dao.NavigationDAO;
import org.reuac.yrt_001.model.NavigationItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NavigationDAOImpl implements NavigationDAO {
    private static final Logger logger = LoggerFactory.getLogger(NavigationDAOImpl.class);

    @Override
    public List<NavigationItem> findByNavArea(String navArea) {
        List<NavigationItem> items = new ArrayList<>();
        String sql = "SELECT * FROM navigation_items WHERE nav_area = ? ORDER BY display_order ASC";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, navArea);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    NavigationItem item = new NavigationItem();
                    item.setNavItemId(rs.getInt("nav_item_id"));
                    item.setNavArea(rs.getString("nav_area"));
                    item.setText(rs.getString("text"));
                    item.setUrl(rs.getString("url"));
                    item.setTargetPageCode(rs.getString("target_page_code"));
                    item.setDisplayOrder(rs.getInt("display_order"));
                    item.setIconClass(rs.getString("icon_class"));
                    item.setIsButton(rs.getBoolean("is_button"));
                    item.setButtonClass(rs.getString("button_class"));
                    item.setTextBeforeButton(rs.getString("text_before_button"));
                    item.setCreatedAt(rs.getTimestamp("created_at"));
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding navigation items by area: " + navArea, e);
        }
        return items;
    }
}
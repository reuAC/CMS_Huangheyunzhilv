package org.reuac.yrt_001.dao;

import org.reuac.yrt_001.model.NavigationItem;

import java.util.List;

public interface NavigationDAO {
    List<NavigationItem> findByNavArea(String navArea);

}
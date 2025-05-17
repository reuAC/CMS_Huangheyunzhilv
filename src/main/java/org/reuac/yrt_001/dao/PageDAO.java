package org.reuac.yrt_001.dao;

import org.reuac.yrt_001.model.Page;

import java.util.List;
import java.util.Optional;

public interface PageDAO {
    Optional<Page> findByPageCode(String pageCode);

    List<Page> findAll();

    boolean update(Page page);

    boolean updateInTransaction(Page page, java.sql.Connection conn);
}
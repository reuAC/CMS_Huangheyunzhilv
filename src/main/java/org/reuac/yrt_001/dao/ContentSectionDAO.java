package org.reuac.yrt_001.dao;

import org.reuac.yrt_001.model.ContentSection;

import java.util.List;
import java.util.Optional;

public interface ContentSectionDAO {
    Optional<ContentSection> findByPageCodeAndIdentifier(String pageCode, String sectionIdentifier);

    List<ContentSection> findByPageCode(String pageCode);

    boolean update(ContentSection section);

    boolean updateInTransaction(ContentSection section, java.sql.Connection conn);
}
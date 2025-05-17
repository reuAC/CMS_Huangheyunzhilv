package org.reuac.yrt_001.service;

import org.reuac.yrt_001.model.ContentSection;
import org.reuac.yrt_001.model.Page;

import java.util.List;
import java.util.Optional;

public interface PageMetaService {
    List<Page> getAllPages();

    Optional<Page> getPageByCode(String pageCode);

    List<ContentSection> getContentSectionsByPageCode(String pageCode);

    boolean updatePageAndSections(Page pageData, List<ContentSection> sectionsData);
}
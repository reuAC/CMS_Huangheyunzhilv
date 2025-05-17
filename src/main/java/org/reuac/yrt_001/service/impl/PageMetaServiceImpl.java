package org.reuac.yrt_001.service.impl;

import org.reuac.yrt_001.config.DataSourceConfig;
import org.reuac.yrt_001.dao.ContentSectionDAO;
import org.reuac.yrt_001.dao.PageDAO;
import org.reuac.yrt_001.dao.impl.ContentSectionDAOImpl;
import org.reuac.yrt_001.dao.impl.PageDAOImpl;
import org.reuac.yrt_001.model.ContentSection;
import org.reuac.yrt_001.model.Page;
import org.reuac.yrt_001.service.PageMetaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PageMetaServiceImpl implements PageMetaService {
    private static final Logger logger = LoggerFactory.getLogger(PageMetaServiceImpl.class);
    private final PageDAO pageDAO = new PageDAOImpl();
    private final ContentSectionDAO contentSectionDAO = new ContentSectionDAOImpl();

    @Override
    public List<Page> getAllPages() {
        return pageDAO.findAll();
    }

    @Override
    public Optional<Page> getPageByCode(String pageCode) {
        return pageDAO.findByPageCode(pageCode);
    }

    @Override
    public List<ContentSection> getContentSectionsByPageCode(String pageCode) {
        return contentSectionDAO.findByPageCode(pageCode);
    }

    @Override
    public boolean updatePageAndSections(Page pageData, List<ContentSection> sectionsData) {
        Connection conn = null;
        try {
            conn = DataSourceConfig.getConnection();
            conn.setAutoCommit(false);

            boolean pageUpdateSuccess = pageDAO.updateInTransaction(pageData, conn);
            if (!pageUpdateSuccess) {
                conn.rollback();
                logger.error("Page update failed for page_code: {}, rolling back.", pageData.getPageCode());
                return false;
            }

            if (sectionsData != null) {
                for (ContentSection section : sectionsData) {
                    boolean sectionUpdateSuccess = contentSectionDAO.updateInTransaction(section, conn);
                    if (!sectionUpdateSuccess) {
                        conn.rollback();
                        logger.error("Content section update failed for section_id: {}, page_code: {}, rolling back.", section.getSectionId(), section.getPageCode());
                        return false;
                    }
                }
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            logger.error("Error during updatePageAndSections transaction for page_code: " + pageData.getPageCode(), e);
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    logger.error("Error rolling back transaction", ex);
                }
            }
            return false;
        } catch (RuntimeException e) {
            logger.error("Runtime error during updatePageAndSections transaction for page_code: " + pageData.getPageCode(), e);
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    logger.error("Error rolling back transaction after runtime exception", ex);
                }
            }
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    logger.error("Error closing connection", e);
                }
            }
        }
    }
}
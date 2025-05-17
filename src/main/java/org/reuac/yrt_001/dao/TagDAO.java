package org.reuac.yrt_001.dao;

import org.reuac.yrt_001.model.Tag;

import java.util.List;

public interface TagDAO {
    List<Tag> findByArticleId(int articleId);

}
package org.reuac.yrt_001.dao;

import org.reuac.yrt_001.model.Card;

import java.util.List;
import java.util.Optional;

public interface CardDAO {
    List<Card> findBySectionId(int sectionId);

    List<Card> findByPageCodeAndType(String pageCode, String cardType);

    List<Card> findByPageCode(String pageCode);

    Optional<Card> findById(int cardId);

    List<Card> findAllPaginated(String filterPageCode, String filterCardType, int limit, int offset);

    int countAllFiltered(String filterPageCode, String filterCardType);

    boolean update(Card card);

    List<String> getAllDistinctPageCodes();

    List<String> getAllDistinctCardTypes();
}
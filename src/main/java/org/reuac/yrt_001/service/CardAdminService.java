package org.reuac.yrt_001.service;

import org.reuac.yrt_001.model.Card;
import org.reuac.yrt_001.model.PaginationData;

import java.util.List;
import java.util.Optional;

public interface CardAdminService {
    Optional<Card> getCardById(int cardId);

    List<Card> getCardsPaginated(String filterPageCode, String filterCardType, int pageNumber, int pageSize);

    PaginationData getCardPaginationData(String filterPageCode, String filterCardType, int pageNumber, int pageSize);

    boolean updateCard(Card card);

    List<String> getAllPageCodesForFilter();

    List<String> getAllCardTypesForFilter();
}
package org.reuac.yrt_001.service.impl;

import org.reuac.yrt_001.dao.CardDAO;
import org.reuac.yrt_001.dao.impl.CardDAOImpl;
import org.reuac.yrt_001.model.Card;
import org.reuac.yrt_001.model.PaginationData;
import org.reuac.yrt_001.service.CardAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class CardAdminServiceImpl implements CardAdminService {
    private static final Logger logger = LoggerFactory.getLogger(CardAdminServiceImpl.class);
    private final CardDAO cardDAO = new CardDAOImpl();

    @Override
    public Optional<Card> getCardById(int cardId) {
        return cardDAO.findById(cardId);
    }

    @Override
    public List<Card> getCardsPaginated(String filterPageCode, String filterCardType, int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return cardDAO.findAllPaginated(filterPageCode, filterCardType, pageSize, offset);
    }

    @Override
    public PaginationData getCardPaginationData(String filterPageCode, String filterCardType, int pageNumber, int pageSize) {
        int totalResults = cardDAO.countAllFiltered(filterPageCode, filterCardType);
        int totalPages = (int) Math.ceil((double) totalResults / pageSize);
        if (totalPages == 0 && totalResults > 0) totalPages = 1;
        if (pageNumber > totalPages && totalPages > 0) pageNumber = totalPages;
        if (pageNumber < 1) pageNumber = 1;
        return new PaginationData(pageNumber, totalPages, totalResults);
    }

    @Override
    public boolean updateCard(Card card) {

        return cardDAO.update(card);
    }

    @Override
    public List<String> getAllPageCodesForFilter() {
        return cardDAO.getAllDistinctPageCodes();
    }

    @Override
    public List<String> getAllCardTypesForFilter() {
        return cardDAO.getAllDistinctCardTypes();
    }
}
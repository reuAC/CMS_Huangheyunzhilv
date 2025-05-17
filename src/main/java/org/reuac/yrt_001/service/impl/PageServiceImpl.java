package org.reuac.yrt_001.service.impl;

import org.reuac.yrt_001.dao.*;
import org.reuac.yrt_001.dao.impl.*;
import org.reuac.yrt_001.model.*;
import org.reuac.yrt_001.service.PageService;

import java.util.*;
import java.util.stream.Collectors;

public class PageServiceImpl implements PageService {
    private final PageDAO pageDAO = new PageDAOImpl();
    private final NavigationDAO navigationDAO = new NavigationDAOImpl();
    private final ContentSectionDAO contentSectionDAO = new ContentSectionDAOImpl();
    private final CardDAO cardDAO = new CardDAOImpl();
    private final CarouselDAO carouselDAO = new CarouselDAOImpl();
    private final ArticleDAO articleDAO = new ArticleDAOImpl();


    @Override
    public Optional<Page> getPageData(String pageCode) {
        return pageDAO.findByPageCode(pageCode);
    }

    @Override
    public List<NavigationItem> getNavigationItems(String navArea) {
        return navigationDAO.findByNavArea(navArea);
    }

    @Override
    public Optional<ContentSection> getContentSection(String pageCode, String sectionIdentifier) {
        return contentSectionDAO.findByPageCodeAndIdentifier(pageCode, sectionIdentifier);
    }

    @Override
    public List<Card> getCardsForSection(int sectionId) {
        return cardDAO.findBySectionId(sectionId);
    }

    @Override
    public List<Card> getCardsForPageByType(String pageCode, String cardType) {
        return cardDAO.findByPageCodeAndType(pageCode, cardType);
    }

    @Override
    public List<Card> getCardsForPage(String pageCode) {
        return cardDAO.findByPageCode(pageCode);
    }

    @Override
    public List<CarouselSlide> getActiveCarouselSlides(String pageCode) {
        return carouselDAO.findByPageCodeAndActive(pageCode, true);
    }

    @Override
    public Optional<Article> getArticleBySlug(String slug) {
        return articleDAO.findBySlug(slug);
    }

    @Override
    public Map<String, List<Card>> getIndexPageSpecialSightsCards(String pageCode) {
        Map<String, List<Card>> groupedCards = new HashMap<>();
        List<Card> allSightsCards = cardDAO.findByPageCodeAndType(pageCode, "index_special_sight");

        groupedCards.put("featuredSightsCards",
                allSightsCards.stream()
                        .filter(c -> "featured_large".equals(c.getMetaInfo1()))
                        .collect(Collectors.toList()));

        List<Card> standardCards = allSightsCards.stream()
                .filter(c -> "standard_small".equals(c.getMetaInfo1()))
                .collect(Collectors.toList());


        int standardRow1Size = Math.min(5, standardCards.size());
        groupedCards.put("standardSightsCardsRow1", new ArrayList<>(standardCards.subList(0, standardRow1Size)));
        if (standardCards.size() > 5) {
            groupedCards.put("standardSightsCardsRow2", new ArrayList<>(standardCards.subList(5, Math.min(10, standardCards.size()))));
        } else {
            groupedCards.put("standardSightsCardsRow2", new ArrayList<>());
        }
        return groupedCards;
    }

    @Override
    public List<Card> getIndexPageServiceFeatureCards(String pageCode) {

        return cardDAO.findByPageCodeAndType(pageCode, "service_feature");
    }

    @Override
    public Map<String, List<Card>> getZijiaYouPageCards(String pageCode) {
        Map<String, List<Card>> groupedCards = new HashMap<>();
        List<Card> allZijiaYouCards = cardDAO.findByPageCode(pageCode);


        int row1Count = 3;
        List<Card> row1Cards = new ArrayList<>();
        List<Card> rowMoreCards = new ArrayList<>();

        for (int i = 0; i < allZijiaYouCards.size(); i++) {
            if (i < row1Count) {
                row1Cards.add(allZijiaYouCards.get(i));
            } else {
                rowMoreCards.add(allZijiaYouCards.get(i));
            }
        }
        groupedCards.put("roadtripCardsRow1", row1Cards);
        groupedCards.put("roadtripCardsRowMore", rowMoreCards);
        return groupedCards;
    }


    @Override
    public List<SearchResult> searchArticles(String searchTerm, int pageNumber, int pageSize) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return new ArrayList<>();
        }
        int offset = (pageNumber - 1) * pageSize;
        return articleDAO.searchArticles(searchTerm.trim(), pageSize, offset);
    }

    @Override
    public PaginationData getSearchPaginationData(String searchTerm, int pageNumber, int pageSize) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return new PaginationData(1, 0, 0);
        }
        int totalResults = articleDAO.countSearchResults(searchTerm.trim());
        int totalPages = (int) Math.ceil((double) totalResults / pageSize);
        return new PaginationData(pageNumber, totalPages, totalResults);
    }

    @Override
    public String getGlobalSetting(String settingName) {


        if ("headerLogoText".equals(settingName)) return "黄河云之旅 LOGO";
        if ("footerCopyrightText".equals(settingName)) return "© " + java.time.Year.now() + " 黄河云之旅. 版权所有.";
        if ("navSearchPlaceholder".equals(settingName)) return "搜索黄河奇景...";
        return null;
    }
}
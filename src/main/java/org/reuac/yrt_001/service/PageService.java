package org.reuac.yrt_001.service;

import org.reuac.yrt_001.model.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PageService {
    Optional<Page> getPageData(String pageCode);

    List<NavigationItem> getNavigationItems(String navArea);

    Optional<ContentSection> getContentSection(String pageCode, String sectionIdentifier);

    List<Card> getCardsForSection(int sectionId);

    List<Card> getCardsForPageByType(String pageCode, String cardType);

    List<Card> getCardsForPage(String pageCode);

    List<CarouselSlide> getActiveCarouselSlides(String pageCode);

    Optional<Article> getArticleBySlug(String slug);


    Map<String, List<Card>> getIndexPageSpecialSightsCards(String pageCode);

    List<Card> getIndexPageServiceFeatureCards(String pageCode);


    Map<String, List<Card>> getZijiaYouPageCards(String pageCode);


    List<SearchResult> searchArticles(String searchTerm, int pageNumber, int pageSize);

    PaginationData getSearchPaginationData(String searchTerm, int pageNumber, int pageSize);

    String getGlobalSetting(String settingName);
}
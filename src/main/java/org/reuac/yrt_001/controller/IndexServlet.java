package org.reuac.yrt_001.controller;

import org.reuac.yrt_001.model.Card;
import org.reuac.yrt_001.model.ContentSection;
import org.reuac.yrt_001.model.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet("/index")
public class IndexServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pageCode = "index";
        loadCommonData(request, pageCode);

        Optional<Page> pageOpt = pageService.getPageData(pageCode);
        if (!pageOpt.isPresent()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Index page data not found.");
            return;
        }
        request.setAttribute("pageData", pageOpt.get());


        Optional<ContentSection> specialSightsSectOpt = pageService.getContentSection(pageCode, "index_special_sights");
        specialSightsSectOpt.ifPresent(section -> request.setAttribute("specialSightsSection", section));

        Map<String, List<Card>> specialSightsCardsMap = pageService.getIndexPageSpecialSightsCards(pageCode);
        request.setAttribute("featuredSightsCards", specialSightsCardsMap.get("featuredSightsCards"));
        request.setAttribute("standardSightsCardsRow1", specialSightsCardsMap.get("standardSightsCardsRow1"));
        request.setAttribute("standardSightsCardsRow2", specialSightsCardsMap.get("standardSightsCardsRow2"));


        Optional<ContentSection> serviceFeaturesSectOpt = pageService.getContentSection(pageCode, "index_service_features");
        serviceFeaturesSectOpt.ifPresent(section -> request.setAttribute("serviceFeaturesSection", section));

        List<Card> serviceFeatureCards = pageService.getIndexPageServiceFeatureCards(pageCode);
        request.setAttribute("serviceFeatureCards", serviceFeatureCards);

        request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
    }
}
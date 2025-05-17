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

@WebServlet("/zijiayou")
public class ZijiayouServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pageCode = "zijiayou";
        loadCommonData(request, pageCode);

        Optional<Page> pageOpt = pageService.getPageData(pageCode);
        if (!pageOpt.isPresent()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "自驾游 page data not found.");
            return;
        }
        request.setAttribute("pageData", pageOpt.get());
        request.setAttribute("pageSpecificCss", "zijiayou.css");


        Optional<ContentSection> roadTripSectOpt = pageService.getContentSection(pageCode, "zijiayou_roadtrip_planner");
        roadTripSectOpt.ifPresent(section -> request.setAttribute("roadTripSection", section));


        Map<String, List<Card>> zijiaYouCardsMap = pageService.getZijiaYouPageCards(pageCode);
        request.setAttribute("roadtripCardsRow1", zijiaYouCardsMap.get("roadtripCardsRow1"));
        request.setAttribute("roadtripCardsRowMore", zijiaYouCardsMap.get("roadtripCardsRowMore"));


        request.getRequestDispatcher("/WEB-INF/jsp/zijiayou.jsp").forward(request, response);
    }
}
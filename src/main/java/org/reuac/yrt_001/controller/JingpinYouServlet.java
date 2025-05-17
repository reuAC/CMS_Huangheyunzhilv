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
import java.util.Optional;

@WebServlet("/jingpinyou")
public class JingpinYouServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pageCode = "jingpinyou";
        loadCommonData(request, pageCode);

        Optional<Page> pageOpt = pageService.getPageData(pageCode);
        if (!pageOpt.isPresent()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "精品游 page data not found.");
            return;
        }
        request.setAttribute("pageData", pageOpt.get());
        request.setAttribute("pageSpecificCss", "jinpinyou.css");


        Optional<ContentSection> tripIdeasSectOpt = pageService.getContentSection(pageCode, "jingpinyou_trip_ideas");
        tripIdeasSectOpt.ifPresent(section -> request.setAttribute("tripIdeasSection", section));


        List<Card> ideaCards = pageService.getCardsForPageByType(pageCode, "idea_placeholder");
        request.setAttribute("ideaCards", ideaCards);

        request.getRequestDispatcher("/WEB-INF/jsp/jingpinyou.jsp").forward(request, response);
    }
}
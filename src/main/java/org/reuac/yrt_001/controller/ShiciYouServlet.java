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

@WebServlet("/shiciyou")
public class ShiciYouServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pageCode = "shiciyou";
        loadCommonData(request, pageCode);

        Optional<Page> pageOpt = pageService.getPageData(pageCode);
        if (!pageOpt.isPresent()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "诗词游 page data not found.");
            return;
        }
        request.setAttribute("pageData", pageOpt.get());
        request.setAttribute("pageSpecificCss", "shiciyou.css");


        Optional<ContentSection> guidesSectOpt = pageService.getContentSection(pageCode, "shiciyou_expert_guides");
        guidesSectOpt.ifPresent(section -> request.setAttribute("guidesSection", section));


        List<Card> guideCards = pageService.getCardsForPageByType(pageCode, "guide_detailed");
        request.setAttribute("guideCards", guideCards);

        request.getRequestDispatcher("/WEB-INF/jsp/shiciyou.jsp").forward(request, response);
    }
}
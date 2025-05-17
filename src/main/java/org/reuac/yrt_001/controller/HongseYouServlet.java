package org.reuac.yrt_001.controller;

import org.reuac.yrt_001.model.CarouselSlide;
import org.reuac.yrt_001.model.ContentSection;
import org.reuac.yrt_001.model.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/hongseyou")
public class HongseYouServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pageCode = "hongseyou";
        loadCommonData(request, pageCode);

        Optional<Page> pageOpt = pageService.getPageData(pageCode);
        if (!pageOpt.isPresent()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "红色游 page data not found.");
            return;
        }
        request.setAttribute("pageData", pageOpt.get());
        request.setAttribute("pageSpecificCss", "hongseyou.css");


        Optional<ContentSection> carouselHeaderOpt = pageService.getContentSection(pageCode, "hongseyou_carousel_header");
        carouselHeaderOpt.ifPresent(section -> request.setAttribute("carouselHeaderSection", section));


        List<CarouselSlide> slides = pageService.getActiveCarouselSlides(pageCode);
        request.setAttribute("carouselSlides", slides);

        request.getRequestDispatcher("/WEB-INF/jsp/hongseyou.jsp").forward(request, response);
    }
}
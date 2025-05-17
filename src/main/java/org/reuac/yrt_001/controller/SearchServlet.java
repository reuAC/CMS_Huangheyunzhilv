package org.reuac.yrt_001.controller;

import org.reuac.yrt_001.model.Page;
import org.reuac.yrt_001.model.PaginationData;
import org.reuac.yrt_001.model.SearchResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/search")
public class SearchServlet extends BaseServlet {
    private static final int DEFAULT_PAGE_SIZE = 10;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pageCode = "search";
        loadCommonData(request, pageCode);


        Optional<Page> pageOpt = pageService.getPageData(pageCode);
        if (pageOpt.isPresent()) {
            request.setAttribute("pageData", pageOpt.get());
        } else {

            Page tempPage = new Page();
            tempPage.setPageCode(pageCode);
            tempPage.setPageTitleSeo("搜索结果 - 黄河云之旅");
            request.setAttribute("pageData", tempPage);
        }
        request.setAttribute("pageSpecificCss", "search.css");
        request.setAttribute("pageSpecificCss2", "main.css");


        String searchTerm = request.getParameter("q");
        String pageParam = request.getParameter("page");
        int currentPage = 1;

        if (pageParam != null) {
            try {
                currentPage = Integer.parseInt(pageParam);
                if (currentPage < 1) currentPage = 1;
            } catch (NumberFormatException e) {
                currentPage = 1;
            }
        }

        request.setAttribute("searchTerm", searchTerm != null ? searchTerm : "");

        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            List<SearchResult> searchResults = pageService.searchArticles(searchTerm, currentPage, DEFAULT_PAGE_SIZE);
            PaginationData paginationData = pageService.getSearchPaginationData(searchTerm, currentPage, DEFAULT_PAGE_SIZE);

            request.setAttribute("searchResults", searchResults);
            request.setAttribute("paginationData", paginationData);
            request.setAttribute("resultsCount", paginationData.getTotalResults());
        } else {
            request.setAttribute("resultsCount", 0);
        }

        request.getRequestDispatcher("/WEB-INF/jsp/search.jsp").forward(request, response);
    }
}
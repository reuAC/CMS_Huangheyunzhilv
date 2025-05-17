package org.reuac.yrt_001.controller;

import org.reuac.yrt_001.model.NavigationItem;
import org.reuac.yrt_001.model.User;
import org.reuac.yrt_001.service.PageService;
import org.reuac.yrt_001.service.impl.PageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseServlet extends HttpServlet {
    protected PageService pageService;

    @Override
    public void init() throws ServletException {
        super.init();
        pageService = new PageServiceImpl();
    }

    protected void loadCommonData(HttpServletRequest request, String currentPageCode) {

        List<NavigationItem> headerNavItems = pageService.getNavigationItems("header_main");
        request.setAttribute("headerNavItems", headerNavItems);


        List<NavigationItem> headerTopRightItems = pageService.getNavigationItems("header_top_right");


        request.setAttribute("headerTopRightNavItems", headerTopRightItems);


        List<NavigationItem> footerNavItems = pageService.getNavigationItems("footer_main");
        request.setAttribute("footerNavItems", footerNavItems);


        request.setAttribute("headerLogoText", pageService.getGlobalSetting("headerLogoText"));
        request.setAttribute("footerCopyrightText", pageService.getGlobalSetting("footerCopyrightText"));
        request.setAttribute("navSearchPlaceholder", pageService.getGlobalSetting("navSearchPlaceholder"));


        boolean showSearchBarInNav = !("article".equals(currentPageCode) || "search".equals(currentPageCode));
        request.setAttribute("showSearchBarInNav", showSearchBarInNav);


        HttpSession session = request.getSession(false);
        User loggedInUser = null;
        if (session != null) {
            loggedInUser = (User) session.getAttribute("loggedInUser");
        }
        request.setAttribute("loggedInUser", loggedInUser);

        if (loggedInUser != null) {

            List<NavigationItem> userNavItems = new ArrayList<>();
            NavigationItem welcomeMsg = new NavigationItem();
            welcomeMsg.setText("欢迎, " + loggedInUser.getUsername());
            welcomeMsg.setUrl("#");
            userNavItems.add(welcomeMsg);


            NavigationItem adminLink = new NavigationItem();
            adminLink.setText("管理后台");
            adminLink.setUrl("admin/dashboard");
            adminLink.setIsButton(false);
            userNavItems.add(adminLink);

            NavigationItem logoutLink = new NavigationItem();
            logoutLink.setText("退出");
            logoutLink.setUrl("logout");
            logoutLink.setIsButton(true);
            logoutLink.setButtonClass("btn-sm btn-outline-secondary");
            userNavItems.add(logoutLink);

            request.setAttribute("headerTopRightNavItems", userNavItems);
        } else {

            List<NavigationItem> defaultTopRightItems = pageService.getNavigationItems("header_top_right");
            request.setAttribute("headerTopRightNavItems", defaultTopRightItems);
        }


    }
}
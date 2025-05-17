package org.reuac.yrt_001.controller.admin;

import org.reuac.yrt_001.controller.BaseServlet;
import org.reuac.yrt_001.model.ContentSection;
import org.reuac.yrt_001.model.Page;
import org.reuac.yrt_001.service.PageMetaService;
import org.reuac.yrt_001.service.impl.PageMetaServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/admin/pagesmeta/*")
public class AdminPagesMetaServlet extends BaseServlet {
    private PageMetaService pageMetaService;

    @Override
    public void init() throws ServletException {
        super.init();
        pageMetaService = new PageMetaServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loadCommonData(request, "admin_pages_meta");
        String action = request.getPathInfo();
        if (action == null) {
            action = "/list";
        }

        clearSessionMessages(request.getSession());

        switch (action) {
            case "/edit":
                showEditForm(request, response);
                break;
            case "/list":
            default:
                listPagesMeta(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loadCommonData(request, "admin_pages_meta");
        String action = request.getPathInfo();

        clearSessionMessages(request.getSession());

        if ("/save".equals(action)) {
            savePagesMeta(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action for POST: " + action);
        }
    }

    private void clearSessionMessages(HttpSession session) {
        if (session != null) {
            session.removeAttribute("successMessage");
            session.removeAttribute("errorMessage");
        }
    }

    private void listPagesMeta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Page> pages = pageMetaService.getAllPages();
        request.setAttribute("pages", pages);
        request.getRequestDispatcher("/WEB-INF/jsp/admin/pages_meta_list.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pageCode = request.getParameter("page_code");
        if (pageCode == null || pageCode.trim().isEmpty()) {
            request.getSession().setAttribute("errorMessage", "页面代码未提供。");
            response.sendRedirect(request.getContextPath() + "/admin/pagesmeta/list");
            return;
        }

        Optional<Page> pageOpt = pageMetaService.getPageByCode(pageCode);
        if (!pageOpt.isPresent()) {
            request.getSession().setAttribute("errorMessage", "未找到页面代码为 '" + pageCode + "' 的页面。");
            response.sendRedirect(request.getContextPath() + "/admin/pagesmeta/list");
            return;
        }

        List<ContentSection> sections = pageMetaService.getContentSectionsByPageCode(pageCode);
        request.setAttribute("pageData", pageOpt.get());
        request.setAttribute("sections", sections);
        request.setAttribute("pageTitle", "编辑页面元数据 - " + pageCode);
        request.getRequestDispatcher("/WEB-INF/jsp/admin/page_meta_form.jsp").forward(request, response);
    }

    private void savePagesMeta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pageIdStr = request.getParameter("pageId");
        String pageCode = request.getParameter("pageCode");

        if (pageIdStr == null || pageCode == null) {
            request.getSession().setAttribute("errorMessage", "无效的页面数据提交。");
            response.sendRedirect(request.getContextPath() + "/admin/pagesmeta/list");
            return;
        }

        Page pageData = new Page();
        try {
            pageData.setPageId(Integer.parseInt(pageIdStr));
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("errorMessage", "无效的页面ID。");
            response.sendRedirect(request.getContextPath() + "/admin/pagesmeta/list?page_code=" + pageCode);
            return;
        }

        pageData.setPageCode(pageCode);
        pageData.setPageTitleSeo(request.getParameter("pageTitleSeo"));
        pageData.setHeroTitle(request.getParameter("heroTitle"));
        pageData.setHeroSubtitle(request.getParameter("heroSubtitle"));
        pageData.setHeroSearchPlaceholder(request.getParameter("heroSearchPlaceholder"));
        pageData.setHeroImageUrl(request.getParameter("heroImageUrl"));
        pageData.setHeroImageAltText(request.getParameter("heroImageAltText"));

        List<ContentSection> sectionsData = new ArrayList<>();
        String[] sectionIds = request.getParameterValues("sectionId");
        if (sectionIds != null) {
            for (String sectionIdStr : sectionIds) {
                ContentSection section = new ContentSection();
                try {
                    section.setSectionId(Integer.parseInt(sectionIdStr));
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute("errorMessage", "无效的内容版块ID。");
                    response.sendRedirect(request.getContextPath() + "/admin/pagesmeta/edit?page_code=" + pageCode);
                    return;
                }
                section.setTitle(request.getParameter("sectionTitle_" + sectionIdStr));
                section.setSubtitle(request.getParameter("sectionSubtitle_" + sectionIdStr));

                sectionsData.add(section);
            }
        }

        boolean success = pageMetaService.updatePageAndSections(pageData, sectionsData);

        if (success) {
            request.getSession().setAttribute("successMessage", "页面 '" + pageCode + "' 的元数据已成功更新。");
            response.sendRedirect(request.getContextPath() + "/admin/pagesmeta/list");
        } else {

            request.setAttribute("errorMessage", "更新页面 '" + pageCode + "' 元数据失败。请检查日志获取更多信息。");
            request.setAttribute("pageData", pageData);
            request.setAttribute("sections", sectionsData);
            request.setAttribute("pageTitle", "编辑页面元数据 - " + pageCode);
            request.getRequestDispatcher("/WEB-INF/jsp/admin/page_meta_form.jsp").forward(request, response);
        }
    }
}
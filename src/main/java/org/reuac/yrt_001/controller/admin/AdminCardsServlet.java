package org.reuac.yrt_001.controller.admin;

import org.reuac.yrt_001.controller.BaseServlet;
import org.reuac.yrt_001.model.Card;
import org.reuac.yrt_001.model.PaginationData;
import org.reuac.yrt_001.service.CardAdminService;
import org.reuac.yrt_001.service.impl.CardAdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/admin/cards/*")
public class AdminCardsServlet extends BaseServlet {
    private static final int DEFAULT_PAGE_SIZE = 10;
    private CardAdminService cardAdminService;

    @Override
    public void init() throws ServletException {
        super.init();
        cardAdminService = new CardAdminServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loadCommonData(request, "admin_cards");
        String action = request.getPathInfo();
        if (action == null) {
            action = "/list";
        }

        clearSessionMessages(request.getSession());

        switch (action) {
            case "/edit":
                showEditCardForm(request, response);
                break;
            case "/list":
            default:
                listCards(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loadCommonData(request, "admin_cards");
        String action = request.getPathInfo();

        clearSessionMessages(request.getSession());

        if ("/save".equals(action)) {
            saveCard(request, response);
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

    private void listCards(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int currentPage = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try {
                currentPage = Integer.parseInt(pageParam);
                if (currentPage < 1) currentPage = 1;
            } catch (NumberFormatException e) { /* ignore */ }
        }

        String filterPageCode = request.getParameter("filterPageCode");
        String filterCardType = request.getParameter("filterCardType");

        List<Card> cards = cardAdminService.getCardsPaginated(filterPageCode, filterCardType, currentPage, DEFAULT_PAGE_SIZE);
        PaginationData pagination = cardAdminService.getCardPaginationData(filterPageCode, filterCardType, currentPage, DEFAULT_PAGE_SIZE);
        List<String> allPageCodes = cardAdminService.getAllPageCodesForFilter();
        List<String> allCardTypes = cardAdminService.getAllCardTypesForFilter();

        request.setAttribute("cards", cards);
        request.setAttribute("pagination", pagination);
        request.setAttribute("allPageCodes", allPageCodes);
        request.setAttribute("allCardTypes", allCardTypes);
        request.setAttribute("currentFilterPageCode", filterPageCode);
        request.setAttribute("currentFilterCardType", filterCardType);

        request.getRequestDispatcher("/WEB-INF/jsp/admin/cards_list.jsp").forward(request, response);
    }

    private void showEditCardForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null) {
            request.getSession().setAttribute("errorMessage", "卡片ID未提供。");
            response.sendRedirect(request.getContextPath() + "/admin/cards/list");
            return;
        }
        try {
            int cardId = Integer.parseInt(idParam);
            Optional<Card> cardOpt = cardAdminService.getCardById(cardId);
            if (cardOpt.isPresent()) {
                request.setAttribute("card", cardOpt.get());
                request.setAttribute("pageTitle", "编辑卡片 - ID: " + cardId);
                request.getRequestDispatcher("/WEB-INF/jsp/admin/card_form.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("errorMessage", "未找到ID为 " + cardId + " 的卡片。");
                response.sendRedirect(request.getContextPath() + "/admin/cards/list");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("errorMessage", "无效的卡片ID格式。");
            response.sendRedirect(request.getContextPath() + "/admin/cards/list");
        }
    }

    private void saveCard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("cardId");
        if (idParam == null || idParam.isEmpty()) {
            request.getSession().setAttribute("errorMessage", "卡片ID丢失，无法保存。");
            response.sendRedirect(request.getContextPath() + "/admin/cards/list");
            return;
        }

        Card card = new Card();
        try {
            card.setCardId(Integer.parseInt(idParam));
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("errorMessage", "无效的卡片ID格式。");
            response.sendRedirect(request.getContextPath() + "/admin/cards/list");
            return;
        }

        card.setPageCode(request.getParameter("pageCode"));
        String sectionIdStr = request.getParameter("sectionId");
        if (sectionIdStr != null && !sectionIdStr.trim().isEmpty()) {
            try {
                card.setSectionId(Integer.parseInt(sectionIdStr.trim()));
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "无效的版块ID格式，必须是数字。");
                request.setAttribute("card", card);
                request.setAttribute("pageTitle", "编辑卡片 - ID: " + card.getCardId());
                fillCardWithError(request, card);
                request.getRequestDispatcher("/WEB-INF/jsp/admin/card_form.jsp").forward(request, response);
                return;
            }
        } else {
            card.setSectionId(null);
        }
        card.setCardType(request.getParameter("cardType"));
        card.setTitle(request.getParameter("title"));
        card.setSubtitle(request.getParameter("subtitle"));
        card.setImageUrl(request.getParameter("imageUrl"));
        card.setImageAltText(request.getParameter("imageAltText"));
        card.setPlaceholderLogoText(request.getParameter("placeholderLogoText"));
        card.setLinkUrl(request.getParameter("linkUrl"));
        card.setLinkText(request.getParameter("linkText"));
        card.setLinkedArticleSlug(request.getParameter("linkedArticleSlug"));
        card.setMetaInfo1(request.getParameter("metaInfo1"));
        card.setMetaInfo2(request.getParameter("metaInfo2"));
        card.setIconSvg(request.getParameter("iconSvg"));
        try {
            card.setDisplayOrder(Integer.parseInt(request.getParameter("displayOrder")));
        } catch (NumberFormatException e) {
            card.setDisplayOrder(0);
            request.setAttribute("errorMessage", "显示顺序必须是数字。");
            fillCardWithError(request, card);
            request.getRequestDispatcher("/WEB-INF/jsp/admin/card_form.jsp").forward(request, response);
            return;
        }

        boolean success = cardAdminService.updateCard(card);
        if (success) {
            request.getSession().setAttribute("successMessage", "卡片 (ID: " + card.getCardId() + ") 已成功更新。");
            response.sendRedirect(request.getContextPath() + "/admin/cards/list");
        } else {
            request.setAttribute("errorMessage", "更新卡片失败。");
            fillCardWithError(request, card);
            request.getRequestDispatcher("/WEB-INF/jsp/admin/card_form.jsp").forward(request, response);
        }
    }

    private void fillCardWithError(HttpServletRequest request, Card card) {
        request.setAttribute("card", card);
        request.setAttribute("pageTitle", "编辑卡片 - ID: " + card.getCardId());


    }
}
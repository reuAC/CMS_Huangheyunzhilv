package org.reuac.yrt_001.filter;

import org.reuac.yrt_001.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminAuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        User loggedInUser = null;
        if (session != null) {
            loggedInUser = (User) session.getAttribute("loggedInUser");
        }

        if (loggedInUser != null) {

            chain.doFilter(request, response);
        } else {


            String requestedUrl = httpRequest.getRequestURI();
            if (httpRequest.getQueryString() != null) {
                requestedUrl += "?" + httpRequest.getQueryString();
            }
            if (session == null) session = httpRequest.getSession();
            session.setAttribute("redirectAfterLogin", requestedUrl);
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        }
    }

    @Override
    public void destroy() {

    }
}
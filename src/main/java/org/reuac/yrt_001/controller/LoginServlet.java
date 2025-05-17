package org.reuac.yrt_001.controller;

import org.reuac.yrt_001.model.User;
import org.reuac.yrt_001.service.UserService;
import org.reuac.yrt_001.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends BaseServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loadCommonData(request, "login");
        if ("success".equals(request.getParameter("registration"))) {
            request.setAttribute("successMessage", "注册成功！请登录。");
        }
        if ("logged_out".equals(request.getParameter("status"))) {
            request.setAttribute("infoMessage", "您已成功退出登录。");
        }
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loadCommonData(request, "login");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String captcha = request.getParameter("captcha");

        HttpSession session = request.getSession();
        String sessionCaptcha = (String) session.getAttribute(CaptchaServlet.CAPTCHA_SESSION_KEY);
        session.removeAttribute(CaptchaServlet.CAPTCHA_SESSION_KEY);

        String errorMessage = null;

        if (username == null || username.trim().isEmpty() ||
                password == null || password.isEmpty() ||
                captcha == null || captcha.trim().isEmpty()) {
            errorMessage = "用户名、密码和验证码不能为空！";
        } else if (sessionCaptcha == null || !sessionCaptcha.equalsIgnoreCase(captcha.trim())) {
            errorMessage = "验证码不正确！";
        } else {
            Optional<User> userOpt = userService.loginUser(username.trim(), password);
            if (userOpt.isPresent()) {
                session.setAttribute("loggedInUser", userOpt.get());

                String redirectUrl = (String) session.getAttribute("redirectAfterLogin");
                if (redirectUrl != null && !redirectUrl.isEmpty()) {
                    session.removeAttribute("redirectAfterLogin");
                    response.sendRedirect(redirectUrl);
                } else {
                    response.sendRedirect(request.getContextPath() + "/index");
                }
                return;
            } else {
                errorMessage = "用户名或密码错误！";
            }
        }

        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.setAttribute("usernameValue", username);
        }
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }
}
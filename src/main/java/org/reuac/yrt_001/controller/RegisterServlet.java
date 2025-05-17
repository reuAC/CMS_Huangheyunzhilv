package org.reuac.yrt_001.controller;

import org.reuac.yrt_001.service.UserService;
import org.reuac.yrt_001.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends BaseServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loadCommonData(request, "register");
        request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loadCommonData(request, "register");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String captcha = request.getParameter("captcha");

        HttpSession session = request.getSession();
        String sessionCaptcha = (String) session.getAttribute(CaptchaServlet.CAPTCHA_SESSION_KEY);
        session.removeAttribute(CaptchaServlet.CAPTCHA_SESSION_KEY);

        String errorMessage = null;

        if (username == null || username.trim().isEmpty() ||
                password == null || password.isEmpty() ||
                confirmPassword == null || confirmPassword.isEmpty() ||
                captcha == null || captcha.trim().isEmpty()) {
            errorMessage = "所有必填字段不能为空！";
        } else if (!password.equals(confirmPassword)) {
            errorMessage = "两次输入的密码不一致！";
        } else if (userService.usernameExists(username.trim())) {
            errorMessage = "用户名已被注册！";
        } else if (email != null && !email.trim().isEmpty() && userService.emailExists(email.trim())) {
            errorMessage = "邮箱已被注册！";
        } else if (sessionCaptcha == null || !sessionCaptcha.equalsIgnoreCase(captcha.trim())) {
            errorMessage = "验证码不正确！";
        } else {

            boolean registered = userService.registerUser(username.trim(), password, email != null ? email.trim() : null);
            if (registered) {
                request.setAttribute("successMessage", "注册成功！您现在可以登录了。");

                response.sendRedirect(request.getContextPath() + "/login?registration=success");
                return;
            } else {
                errorMessage = "注册失败，请稍后再试。";
            }
        }

        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);

            request.setAttribute("usernameValue", username);
            request.setAttribute("emailValue", email);
        }
        request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
    }
}
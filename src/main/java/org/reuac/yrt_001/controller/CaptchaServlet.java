package org.reuac.yrt_001.controller;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {
    public static final String CAPTCHA_SESSION_KEY = "captcha_text";
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 120;
    private static final int HEIGHT = 40;
    private static final int CODE_LENGTH = 4;
    private static final String CHAR_LIST = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random RANDOM = new Random();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/png");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        HttpSession session = request.getSession();
        String captchaText = generateRandomString(CODE_LENGTH);
        session.setAttribute(CAPTCHA_SESSION_KEY, captchaText.toLowerCase());

        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();


        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, WIDTH, HEIGHT);


        g.setFont(new Font("Arial", Font.BOLD, 30));


        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 10; i++) {
            int x = RANDOM.nextInt(WIDTH);
            int y = RANDOM.nextInt(HEIGHT);
            int xl = RANDOM.nextInt(12);
            int yl = RANDOM.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }


        for (int i = 0; i < captchaText.length(); i++) {
            g.setColor(new Color(20 + RANDOM.nextInt(110), 20 + RANDOM.nextInt(110), 20 + RANDOM.nextInt(110)));

            double angle = (RANDOM.nextDouble() - 0.5) * 0.5;
            g.rotate(angle, 15 + i * 25, 25);
            g.drawString(String.valueOf(captchaText.charAt(i)), 15 + i * 25, 30 + RANDOM.nextInt(5) - 2);
            g.rotate(-angle, 15 + i * 25, 25);
        }
        g.dispose();

        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
        os.close();
    }

    private String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHAR_LIST.charAt(RANDOM.nextInt(CHAR_LIST.length())));
        }
        return sb.toString();
    }

    private Color getRandColor(int fc, int bc) {
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + RANDOM.nextInt(bc - fc);
        int g = fc + RANDOM.nextInt(bc - fc);
        int b = fc + RANDOM.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
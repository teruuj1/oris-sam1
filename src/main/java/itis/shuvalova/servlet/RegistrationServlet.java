package itis.shuvalova.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "registrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("registration.html");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if ("login".equalsIgnoreCase(login) && "password".equals(password)){
            // session
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("user", login);
            httpSession.setMaxInactiveInterval(60 * 60);
            // cookie
            Cookie cookie = new Cookie("user", login);
            cookie.setMaxAge(24 * 60 * 60);
            resp.addCookie(cookie);
            resp.sendRedirect("main.html");
        } else {
            resp.sendRedirect("/registration");
        }
    }
}
package itis.shuvalova.servlet;

import itis.shuvalova.dao.impl.UserDaoImpl;
import itis.shuvalova.entity.User;
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
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");

        UserDaoImpl userDao = new UserDaoImpl();
        User user = new User(userDao.getAll().size()+1, name, lastname, login, password);
        userDao.save(user);

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("user", login);
        httpSession.setMaxInactiveInterval(60 * 60);

        Cookie cookie = new Cookie("user", login);
        cookie.setMaxAge(24 * 60 * 60);
        resp.addCookie(cookie);
        resp.sendRedirect("/main");
    }
}
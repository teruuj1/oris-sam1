package itis.shuvalova.servlet;

import itis.shuvalova.dao.impl.UserDaoImpl;
import itis.shuvalova.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "authServlet", urlPatterns = "/index")
public class AuthorisationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.html");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = new UserDaoImpl().getByLogin(login);
        if (user!=null && user.getPassword().equals(password)) {
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("user", login);
            httpSession.setMaxInactiveInterval(60 * 60);
            resp.sendRedirect("main.jsp");
        } else {
            resp.sendRedirect("/index");
        }
    }
}


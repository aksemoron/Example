package servlet;

import dao.ConnectionToBase;
import dao.UserDao;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/index")
public class MainServlet extends HttpServlet {
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.createTable();
        if (req.getParameter("idToDelete") != null) {
            if (!(userService.deleteUserById(req.getParameter("idToDelete")))) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

        }
        req.setAttribute("users", userService.getAllUsers());
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!(userService.addUser(req.getParameter("name")))) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        req.setAttribute("users", userService.getAllUsers());
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}

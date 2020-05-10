package servlet;

import dao.UserDAO;
import dao.UserDaoFactory;
import model.User;
import service.UserHybernateService;
import service.UserService;
import util.DBHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/index")
public class MainServlet extends HttpServlet {
    UserDaoFactory userDaoFactory = new UserDaoFactory();
    UserDAO userDAO = userDaoFactory.getUserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", userDAO.getAll());
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("name"));
        if (userDAO.add(user)) {
            req.setAttribute("users", userDAO.getAll());
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}

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

@WebServlet(value = "/update")
public class UpdateServlet extends HttpServlet {

    UserDaoFactory userDaoFactory = new UserDaoFactory();
    UserDAO userDAO = userDaoFactory.getUserDao();
    User user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user = (User) userDAO.getById(new User(Long.valueOf(req.getParameter("idToUpdate"))));
        req.setAttribute("user", "id: " + user.getId() + ",  Имя: " + user.getName());
        req.getRequestDispatcher("/update.jsp").forward(req, resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user.setName(req.getParameter("name"));
        if (userDAO.modifyUserById(user, user.getId())) {
            resp.getWriter().println("User has been updated succesfully");
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.getWriter().println("User not updated");
        }


    }
}

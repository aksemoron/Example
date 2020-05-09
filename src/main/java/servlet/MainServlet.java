package servlet;

import model.User;
import org.hibernate.SessionFactory;
import service.UserHybernateService;
import service.UserService;
import util.ConnectionToBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/index")
public class MainServlet extends HttpServlet {
    UserService userService = new UserService();
    UserHybernateService userHybernateService = new UserHybernateService(ConnectionToBase.getSessionFactory());


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.createTable();
        req.setAttribute("users", userHybernateService.getAll());
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("name"));
        if (userHybernateService.add(user)){
            req.setAttribute("users", userHybernateService.getAll());
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }


    }
}

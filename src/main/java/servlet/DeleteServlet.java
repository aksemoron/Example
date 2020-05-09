package servlet;

import model.User;
import service.UserHybernateService;
import service.UserService;
import util.ConnectionToBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/delete")
public class DeleteServlet extends HttpServlet {
    UserService userService= new UserService();
    UserHybernateService userHybernateService = new UserHybernateService(ConnectionToBase.getSessionFactory());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            if (!(userHybernateService.deleteById(new User(Long.valueOf(req.getParameter("idToDelete")))))) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        req.setAttribute("users", userService.getAllUsers());
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}

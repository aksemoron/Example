package servlet.adminPages;

import model.User;
import service.ServiseUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/index")
public class AdminIndex extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", ServiseUser.getInstance().getAll());
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("name"));
        user.setPassword(req.getParameter("password"));
        user.setRole(req.getParameter("role"));
        if (ServiseUser.getInstance().add(user)) {
            req.setAttribute("users", ServiseUser.getInstance().getAll());
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}

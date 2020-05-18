package servlet;

import model.User;
import service.ServiseUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ServiseUser.getInstance().logIN(new User(req.getParameter("name"), req.getParameter("password")));
        HttpSession session = req.getSession();
        session.setAttribute("role", user.getRole());
        session.setAttribute("idUser", user.getId());
        if (user.getRole().equals("admin")) {
            resp.sendRedirect(req.getContextPath() + "/admin/index");
        }
        if (user.getRole().equals("user")) {
            resp.sendRedirect(req.getContextPath() + "/user/index");
        }
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}

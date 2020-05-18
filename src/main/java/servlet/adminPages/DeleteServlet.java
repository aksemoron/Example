package servlet;


import model.User;
import service.ServiseUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/delete")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            if (!(ServiseUser.getInstance().deleteById(new User(Long.valueOf(req.getParameter("idToDelete")))))) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        req.setAttribute("users", ServiseUser.getInstance().getAll());
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}

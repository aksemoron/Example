package servlet.adminPages;


import model.User;
import service.ServiseUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/admin/update")
public class AdminUpdateServlet extends HttpServlet {

//параметр и атрибут в чем разница
    //в какой момент времени у хибернейта выполняется запрос в бд
    //персинстанс контекст

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ServiseUser.getInstance().getById(new User(Long.valueOf(req.getParameter("idToUpdate"))));
        req.setAttribute("user", user);
        req.getRequestDispatcher("/update.jsp").forward(req, resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setId(Long.valueOf(req.getParameter("id")));
        user.setName(req.getParameter("name"));
        user.setPassword(req.getParameter("password"));
        user.setRole(req.getParameter("role"));
        if (ServiseUser.getInstance().modifyUserById(user, user.getId())) {
            resp.sendRedirect(req.getContextPath() + "/admin/index");
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.getWriter().println("User not updated");
        }
    }
}

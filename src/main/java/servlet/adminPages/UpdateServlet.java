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

@WebServlet(value = "/admin/update")
public class UpdateServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User  user =  ServiseUser.getInstance().getById(new User(Long.valueOf(req.getParameter("idToUpdate"))));
        HttpSession session = req.getSession();
        session.setAttribute("id", user.getId());
        req.setAttribute("user", "id: " + user.getId() + ",  Имя: " + user.getName()+", пароль: "+user.getPassword()+
                ", роль: "+user.getRole());
        req.getRequestDispatcher("/update.jsp").forward(req, resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        HttpSession session = req.getSession();
        user.setId((Long) session.getAttribute("id"));
        user.setName(req.getParameter("name"));
        user.setPassword(req.getParameter("password"));
        user.setRole(req.getParameter("role"));
        if (ServiseUser.getInstance().modifyUserById(user, user.getId())) {
            resp.getWriter().println("User has been updated succesfully");
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.getWriter().println("User not updated");
        }


    }
}

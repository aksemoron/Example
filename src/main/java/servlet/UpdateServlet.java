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

@WebServlet(value = "/update")
public class UpdateServlet extends HttpServlet {

    UserHybernateService userHybernateService = new UserHybernateService(ConnectionToBase.getSessionFactory());
    UserService userService= new UserService();
    User user;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user = userHybernateService.getById(new User(Long.valueOf(req.getParameter("idToUpdate"))));
        req.setAttribute("user", "id: "+user.getId()+",  Имя: "+user.getName());
        req.getRequestDispatcher("/update.jsp").forward(req, resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user.setName(req.getParameter("name"));
        if (userHybernateService.modifyById(user.getId(),user)){
            resp.getWriter().println("User has been updated succesfully");
            resp.setStatus(HttpServletResponse.SC_OK);
        }else {
            resp.getWriter().println("User not updated");
        }


    }
}

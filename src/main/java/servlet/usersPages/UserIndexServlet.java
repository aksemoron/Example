package servlet.usersPages;

import model.User;
import service.ServiseUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(value = "/user/index")
public class UserIndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = ServiseUser.getInstance().getById(new User((Long) session.getAttribute("idUser")));
        req.setAttribute("user", "id: " + user.getId() + ",  Имя: " + user.getName() + ", пароль: " + user.getPassword() +
                ", роль: " + user.getRole());
        req.getRequestDispatcher("/userPage.jsp").forward(req, resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}

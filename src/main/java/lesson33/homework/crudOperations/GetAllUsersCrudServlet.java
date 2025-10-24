package lesson33.homework.crudOperations;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lesson33.homework.User;
import lesson33.homework.UserDAO;
import java.io.IOException;
import java.util.List;

@WebServlet("/crud-all")
public class GetAllUsersCrudServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<User> users = userDAO.getAllUsers();

        if (users.isEmpty()) {
            req.setAttribute("message", "Нет пользователей в базе данных");
            req.setAttribute("messageType", "info");
        } else {
            req.setAttribute("allUsers", users);
            req.setAttribute("message", "Найдено " + users.size() + " пользователей");
            req.setAttribute("messageType", "success");
        }

        req.getRequestDispatcher("/pages/lesson33/taskOne.jsp").forward(req, resp);
    }
}
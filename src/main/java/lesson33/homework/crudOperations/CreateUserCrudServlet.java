package lesson33.homework.crudOperations;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lesson33.homework.User;
import lesson33.homework.UserDAO;
import java.io.IOException;

@WebServlet("/crud-create")
public class CreateUserCrudServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);

        boolean success = userDAO.createUser(user);

        if (success) {
            req.setAttribute("message", "Пользователь успешно создан!");
            req.setAttribute("messageType", "success");
        } else {
            req.setAttribute("message", "Ошибка при создании пользователя");
            req.setAttribute("messageType", "error");
        }

        req.getRequestDispatcher("/pages/lesson33/taskOne.jsp").forward(req, resp);
    }
}
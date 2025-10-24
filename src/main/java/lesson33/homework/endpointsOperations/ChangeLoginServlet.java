package lesson33.homework.endpointsOperations;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lesson33.homework.User;
import lesson33.homework.UserDAO;
import java.io.IOException;

@WebServlet("/change-login")
public class ChangeLoginServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("id");
        String login = req.getParameter("login");

        if (idParam == null || login == null || idParam.isEmpty() || login.isEmpty()) {
            req.setAttribute("message", "Ошибка: параметры id и login обязательны");
            req.setAttribute("messageType", "error");
        } else {
            try {
                int id = Integer.parseInt(idParam);
                User existingUser = userDAO.getUserById(id);

                if (existingUser == null) {
                    req.setAttribute("message", "Пользователь с ID " + id + " не найден");
                    req.setAttribute("messageType", "error");
                } else {
                    boolean success = userDAO.updateUser(id, existingUser.getName(), login);

                    if (success) {
                        req.setAttribute("message", "Логин пользователя успешно изменен!");
                        req.setAttribute("messageType", "success");
                        // Показываем обновленные данные
                        User updatedUser = userDAO.getUserById(id);
                        req.setAttribute("foundUser", updatedUser);
                    } else {
                        req.setAttribute("message", "Ошибка при изменении логина");
                        req.setAttribute("messageType", "error");
                    }
                }
            } catch (NumberFormatException e) {
                req.setAttribute("message", "Ошибка: ID должен быть числом");
                req.setAttribute("messageType", "error");
            }
        }

        req.getRequestDispatcher("/pages/lesson33/taskAsterisk.jsp").forward(req, resp);
    }
}
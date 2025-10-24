package lesson33.homework.crudOperations;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lesson33.homework.User;
import lesson33.homework.UserDAO;
import java.io.IOException;

@WebServlet("/crud-update")
public class UpdateUserCrudServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("id");
        String name = req.getParameter("name");
        String username = req.getParameter("username");

        if (idParam == null || idParam.isEmpty()) {
            req.setAttribute("message", "Ошибка: параметр id обязателен");
            req.setAttribute("messageType", "error");
        } else {
            try {
                int id = Integer.parseInt(idParam);
                User existingUser = userDAO.getUserById(id);

                if (existingUser == null) {
                    req.setAttribute("message", "Пользователь с ID " + id + " не найден");
                    req.setAttribute("messageType", "error");
                } else {
                    // Используем новый метод для обновления всех данных
                    boolean success = userDAO.updateUser(id, name, username);

                    if (success) {
                        req.setAttribute("message", "Данные пользователя успешно обновлены!");
                        req.setAttribute("messageType", "success");
                        // Показываем обновленные данные
                        User updatedUser = userDAO.getUserById(id);
                        req.setAttribute("foundUser", updatedUser);
                    } else {
                        req.setAttribute("message", "Ошибка при обновлении данных");
                        req.setAttribute("messageType", "error");
                    }
                }
            } catch (NumberFormatException e) {
                req.setAttribute("message", "Ошибка: ID должен быть числом");
                req.setAttribute("messageType", "error");
            }
        }

        req.getRequestDispatcher("/pages/lesson33/taskOne.jsp").forward(req, resp);
    }
}
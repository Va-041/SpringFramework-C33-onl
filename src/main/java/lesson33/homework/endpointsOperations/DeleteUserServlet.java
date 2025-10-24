package lesson33.homework.endpointsOperations;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lesson33.homework.UserDAO;
import java.io.IOException;

@WebServlet("/delete-user")
public class DeleteUserServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            req.setAttribute("message", "Ошибка: параметр id обязателен");
            req.setAttribute("messageType", "error");
        } else {
            try {
                int id = Integer.parseInt(idParam);
                boolean success = userDAO.deleteUser(id);

                if (success) {
                    req.setAttribute("message", "Пользователь с ID " + id + " успешно удален!");
                    req.setAttribute("messageType", "success");
                } else {
                    req.setAttribute("message", "Ошибка: пользователь с ID " + id + " не найден");
                    req.setAttribute("messageType", "error");
                }
            } catch (NumberFormatException e) {
                req.setAttribute("message", "Ошибка: ID должен быть числом");
                req.setAttribute("messageType", "error");
            }
        }

        req.getRequestDispatcher("/pages/lesson33/taskAsterisk.jsp").forward(req, resp);
    }
}
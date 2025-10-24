package lesson33.homework.crudOperations;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lesson33.homework.User;
import lesson33.homework.UserDAO;
import java.io.IOException;

@WebServlet("/crud-get")
public class GetUserCrudServlet extends HttpServlet {

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
                User user = userDAO.getUserById(id);

                if (user != null) {
                    req.setAttribute("foundUser", user);
                    req.setAttribute("message", "Пользователь найден");
                    req.setAttribute("messageType", "success");
                } else {
                    req.setAttribute("message", "Пользователь с ID " + id + " не найден");
                    req.setAttribute("messageType", "error");
                }
            } catch (NumberFormatException e) {
                req.setAttribute("message", "Ошибка: ID должен быть числом");
                req.setAttribute("messageType", "error");
            }
        }

        req.getRequestDispatcher("/pages/lesson33/taskOne.jsp").forward(req, resp);
    }
}
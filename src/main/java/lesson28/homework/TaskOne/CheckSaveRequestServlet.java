package lesson28.homework.TaskOne;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet("/check-save-request")
public class CheckSaveRequestServlet extends HttpServlet {

    //регулярка для емайла
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+-]{5,}@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");

        boolean hasErrors = false;

        //валидация имени
        if (name == null || name.trim().isEmpty()) {
            request.setAttribute("nameError", "Поле имени обязательно для заполнения");
            hasErrors = true;
        } else {
            name = name.trim();
            if (name.length() < 3) {
                request.setAttribute("nameError", "Имя слишком короткое (минимум 3 символа)");
                hasErrors = true;
            } else if (name.length() > 20) {
                request.setAttribute("nameError", "Имя слишком длинное (максимум 20 символов)");
                hasErrors = true;
            }
        }

        //валидация email
        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("emailError", "Поле email обязательно для заполнения");
            hasErrors = true;
        } else {
            email = email.trim();
            if (!email.contains("@")) {
                request.setAttribute("emailError", "Email должен содержать @");
                hasErrors = true;
            } else if (!EMAIL_PATTERN.matcher(email).matches()) {
                request.setAttribute("emailError",
                        "Некорректный email. Пример: example@domain.com (минимум 5 символов до @)");
                hasErrors = true;
            }
        }

        //валидация сообщения
        if (message == null || message.trim().isEmpty()) {
            request.setAttribute("messageError", "Поле сообщения обязательно для заполнения");
            hasErrors = true;
        } else {
            message = message.trim();
            if (message.length() < 5) {
                request.setAttribute("messageError", "Сообщение слишком короткое (минимум 5 символов)");
                hasErrors = true;
            }
        }

        if (hasErrors) {
            //save введенных значений для повторного отображения
            request.setAttribute("nameValue", name);
            request.setAttribute("emailValue", email);
            request.setAttribute("messageValue", message);

            request.getRequestDispatcher("/WEB-INF/lesson28/save-request.jsp").forward(request, response);
            return;
        }

        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("message", message);

        request.getRequestDispatcher("/WEB-INF/lesson28/success.jsp").forward(request, response);
    }
}
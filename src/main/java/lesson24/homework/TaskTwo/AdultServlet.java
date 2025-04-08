package lesson24.homework.TaskTwo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/taskTwo")
public class AdultServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        try {
            int age = Integer.parseInt(request.getParameter("age"));

            String result;
            if (age >= 18) {
                result = "Вам " + age + " лет. Вы совершеннолетний!";
            } else {
                result = "Вам " + age + " лет. Вы несовершеннолетний!";
            }

            response.getWriter().println("""
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Результат проверки вашего возраста</title>
                    <meta charset="UTF-8">
                </head>
                <body>
                    <h1>Результат проверки</h1>
                    <p>%s</p>
                    <a href="/SpringFrame_war/taskTwoPage">Проверить ещё раз</a>
                    <br>
                    <a href="/SpringFrame_war/home">На главную</a>
                </body>
                </html>
            """.formatted(result));

        } catch (NumberFormatException e) {
            response.getWriter().println("""
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Ошибка</title>
                    <meta charset="UTF-8">
                </head>
                <body>
                    <h1>Ошибка ввода</h1>
                    <p style='color:red'>Пожалуйста, введите корректный возраст!</p>
                    <a href="/SpringFrame_war/adult.html">Попробовать снова</a><br>
                    <a href="/SpringFrame_war/home">На главную</a>
                </body>
                </html>
            """);
        }
    }
}
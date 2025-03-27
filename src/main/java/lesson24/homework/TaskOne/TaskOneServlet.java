package lesson24.homework.TaskOne;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/taskOne")
public class TaskOneServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("""
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Задание 1</title>
                </head>
                <body>
                    <h1>Задание №1</h1>
                    <h2>Узнать время в Минске</h2>
                    <p><a href="/SpringFrame_war/minsk-time">*тык*</a></p>
                   \s
                    <h2>Узнать время в Вашингтоне (США)</h2>
                    <p><a href="/SpringFrame_war/washington-time">*тык*</a></p>
                   \s
                    <h2>Узнать время в Пекине (Китай)</h2>
                    <p><a href="/SpringFrame_war/pekin-time">*тык*</a></p>
                   \s
                    <a href="/SpringFrame_war/home">На главную</a>
                </body>
                </html>
        """);
    }
}



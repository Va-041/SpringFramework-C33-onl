package lesson26.homework.TaskOne;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/logo")
public class GetLogoServlet extends HttpServlet {

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
                <h2>Вывести лого TMS</h2>
                <img src="images/logo.jpg" alt="Лого TMS" width="200">
                <br>
                <a href="/SpringFrame_war/home-26">На главную</a>
            </body>
            </html>
    """);
    }
}

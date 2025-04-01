package lesson25.homework;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home-book")
public class HomePageBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().println("""
            <!DOCTYPE html>
            <html>
            <head>
                <title>Главная</title>
            </head>
            <body>
                <h1>Домашнее задание №25</h1>
                <p><a href="/SpringFrame_war/download-book">Скачать книгу с сервера</a></p>
                <p><a href="/SpringFrame_war/upload-book">Загрузить книгу на сервер</a></p>
            </body>
            </html>
            """);
    }
}
package lesson24.homework.TaskOne;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/minsk-time")
public class MinskTimeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ZonedDateTime minskTime = ZonedDateTime.now(ZoneId.of("Europe/Minsk"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String formattedTime = minskTime.format(formatter);

        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println("""
            <!DOCTYPE html>
            <html>
            <head>
                <title>Время в Минске</title>
                <meta http-equiv="refresh" content="1">
            </head>
            <body>
                <h1>Региональное время: Минск</h1>
                <p>Текущее время: %s</p>
                <a href="/SpringFrame_war/taskOne">Назад</a>
                <a href="/SpringFrame_war/home">На главную</a>
            </body>
            </html>
        """.formatted(formattedTime));
    }
}
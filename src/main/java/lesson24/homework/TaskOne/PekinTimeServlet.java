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

@WebServlet("/pekin-time")
public class PekinTimeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ZonedDateTime minskTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String formattedTime = minskTime.format(formatter);

        response.setContentType("text/html");
        response.getWriter().println("""
            <!DOCTYPE html>
            <html>
            <head>
                <title>Время в Пекине (Китай)</title>
                <meta http-equiv="refresh" content="1">
            </head>
            <body>
                <h1>Региональное время: Пекин</h1>
                <p>Текущее время: %s</p>
                <a href="/SpringFrame_war/taskOne">Назад</a>
                <a href="/SpringFrame_war/home">На главную</a>
            </body>
            </html>
        """.formatted(formattedTime));
    }
}
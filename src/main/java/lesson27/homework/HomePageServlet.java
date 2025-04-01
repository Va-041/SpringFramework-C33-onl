package lesson27.homework;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/home-27")
public class HomePageServlet extends HttpServlet {

    @Override
    public void init() {
        System.out.println("!!! Servlet was initialized !!!");
    }

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
                    <h1>Домашнее задание №27</h1>
                    <p><a href="/SpringFrame_war/bootstrap-hero">Задание 1</a></p>
                </body>
                </html>
        """);
    }


    @Override
    public void destroy() {
        System.out.println("!!! Servlet was destroyed !!!");
    }

}

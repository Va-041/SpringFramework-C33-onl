package lesson33.homework;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home-33")
public class HomePageServlet extends HttpServlet {


    @Override
    public void init() {
        System.out.println("!!! Servlet was initialized !!!");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/pages/lesson33/greeting.jsp").forward(req, resp);

    }

    @Override
    public void destroy() {
        System.out.println("!!! Servlet was destroyed !!!");
    }
}


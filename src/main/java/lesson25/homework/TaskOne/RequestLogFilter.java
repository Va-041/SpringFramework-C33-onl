package lesson25.homework.TaskOne;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

@WebFilter("/*")
public class RequestLogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("!!! The app is running !!!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        System.out.println("\n------------------------------------------------------------------------------------\n" +
                "Request date: [" + new Date() + "] \nRequest: " + httpRequest.getRequestURI() +
                "\n------------------------------------------------------------------------------------");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("!!! The app was stopped !!!");
    }
}
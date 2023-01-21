package edu.school21.cinema.filters;

import edu.school21.cinema.models.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null && (uri.endsWith("/signIn") || uri.endsWith("/signUp"))) {
            res.sendRedirect("/Cinema-1.0-SNAPSHOT/profile");
        } else if (user == null && uri.endsWith("/profile")) {
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            chain.doFilter(request, response);
        }
    }
}

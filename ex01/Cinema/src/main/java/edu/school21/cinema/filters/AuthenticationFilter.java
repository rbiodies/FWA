package edu.school21.cinema.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class AuthenticationFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");

        if (Objects.equals(user, "authenticated") && (uri.endsWith("/signIn") || uri.endsWith("/signUp"))) {
            res.sendRedirect("/profile");
        } else if (!Objects.equals(user, "authenticated") && uri.endsWith("/profile")) {
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            chain.doFilter(request, response);
        }
    }
}

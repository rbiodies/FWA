package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        request.setAttribute("name", user.getFirstName() + " " + user.getLastName());
        request.setAttribute("email", user.getEmail());

        request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(request, response);
    }
}

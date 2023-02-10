package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.DataService;
import edu.school21.cinema.services.ImagesService;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private static final String LOCALHOST_v6 = "0:0:0:0:0:0:0:1";
    private static final String LOCALHOST_v4 = "127.0.0.1";
    private DataService dataService;
    private ImagesService imagesService;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.dataService = springContext.getBean(DataService.class);
        this.imagesService = springContext.getBean(ImagesService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        dataService.save(user, getClientDate(), getClientTime(), getClientIP(request));

        user.setData(dataService.findByUser(user));
        user.setImages(imagesService.findByUser(user));

        request.setAttribute("user", user);

        request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(request, response);
    }

    private String getClientDate() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy").withLocale(Locale.ENGLISH);
        return now.format(formatter);
    }

    private String getClientTime() {
        LocalTime now = LocalTime.now();
        return now.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    private String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("X-FORWARDED-FOR");

        if (ip == null || ip.isEmpty()) {
            ip = request.getRemoteAddr();
        }
        if (ip.equals(LOCALHOST_v6)) {
            ip = LOCALHOST_v4;
        }

        return ip;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        response.sendRedirect("/Cinema-1.0-SNAPSHOT");
    }
}

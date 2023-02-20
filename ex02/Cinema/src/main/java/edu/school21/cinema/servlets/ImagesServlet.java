package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.ImagesService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Properties;

@WebServlet("/images")
@MultipartConfig
public class ImagesServlet extends HttpServlet {

    private ImagesService imagesService;
    private final String storagePath = getStoragePath();

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.imagesService = springContext.getBean(ImagesService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String uniqueName = imagesService.getLastImageByUser(user).getUniqueName();
        FileInputStream fis = new FileInputStream(storagePath + File.separator + uniqueName);
        IOUtils.copy(fis, response.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String fileName = null;
        String uniqueName = null;
        String filePath = null;

        for (Part part : request.getParts()) {
            fileName = part.getSubmittedFileName();
            uniqueName = imagesService.getCountImages() + 1 + "." + FilenameUtils.getExtension(fileName);
            filePath = System.getProperty("user.dir") + File.separator + storagePath + File.separator + uniqueName;
            part.write(filePath);
        }

        if (fileName != null && !fileName.equals("")) {
            File file = new File(filePath);
            imagesService.save(user, fileName, getSize(file), getMime(file), uniqueName);
        }

        response.sendRedirect("/Cinema-1.0-SNAPSHOT/profile");
    }

    private String getStoragePath() {
        String storagePath = null;

        try (InputStream input = new FileInputStream("src/main/webapp/WEB-INF/application.properties")) {
            Properties prop = new Properties();

            prop.load(input);

            storagePath = prop.getProperty("storage.path");

            File fileSaveDir = new File(storagePath);

            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return storagePath;
    }

    private String getSize(File file) {
        if (file.exists() && file.isFile()) {

            long bytes = file.length();

            long kilobytes = (bytes / 1024);

            long megabytes = (kilobytes / 1024);

            if (megabytes != 0) {
                return megabytes + "M";
            } else if (kilobytes != 0) {
                return kilobytes + "KB";
            } else {
                return bytes + "B";
            }
        }
        System.out.println("File does not exist!");
        return null;
    }

    private String getMime(File file) throws IOException {
        URLConnection connection = file.toURL().openConnection();

        return connection.getContentType();
    }
}

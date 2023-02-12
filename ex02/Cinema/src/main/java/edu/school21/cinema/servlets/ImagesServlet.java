package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.ImagesService;
import edu.school21.cinema.services.UsersService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.net.URLConnection;
import java.util.Properties;

@WebServlet("/images")
@MultipartConfig
public class ImagesServlet extends HttpServlet {

    private ImagesService imagesService;
    private UsersService usersService;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.imagesService = springContext.getBean(ImagesService.class);
        this.usersService = springContext.getBean(UsersService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        this doesn't work for me, so i used temporary solution
//        try (InputStream input = new FileInputStream("src/main/webapp/WEB-INF/application.properties")) {
        try (InputStream input = new FileInputStream("C:\\Users\\user\\Desktop\\newer_fwa\\ex02\\Cinema\\src\\main\\webapp\\WEB-INF\\application.properties")) {

            Properties prop = new Properties();
            prop.load(input);
            String savePath = prop.getProperty("storage.path");
//                String savePath = System.getProperty("user.dir") + File.separator + storagePath;
            File fileSaveDir = new File(savePath);

            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }

            String fileName = null;
            for (Part part : request.getParts()) {
                fileName = part.getSubmittedFileName();
            }

            if (fileName != null && !fileName.equals("")) {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                String uniqueName = imagesService.getLastId() + 1 + "." + FilenameUtils.getExtension(fileName);
                String filePath = savePath + File.separator + uniqueName;
                for (Part part : request.getParts()) {
                    part.write(filePath);
                }
                File file = new File(filePath);
                user.setFilePath(File.separator + "Cinema-1.0-SNAPSHOT" + File.separator + "images" + File.separator + uniqueName);
                imagesService.save(user, fileName, getSize(file), getMime(file), uniqueName);
                user.setImages(imagesService.findByUser(user));
                request.setAttribute("user", user);
                usersService.saveAvatar(user.getId(), filePath);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        response.sendRedirect("/profile");
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/jpeg");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println(user.getPathToAvatar());
        FileInputStream fis = new FileInputStream(user.getPathToAvatar());
        IOUtils.copy(fis, response.getOutputStream());
    }
}

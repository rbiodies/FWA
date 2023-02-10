package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.ImagesService;
import org.apache.commons.io.FilenameUtils;
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

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.imagesService = springContext.getBean(ImagesService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try (InputStream input = new FileInputStream("src/main/webapp/WEB-INF/application.properties")) {

            Properties prop = new Properties();

            prop.load(input);

            String storagePath = prop.getProperty("storage.path");

            String savePath = System.getProperty("user.dir") + File.separator + storagePath;

            File fileSaveDir = new File(savePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }

            String filePath = null;
            String fileName = null;
            for (Part part : request.getParts()) {
                fileName = part.getSubmittedFileName();
                filePath = savePath + File.separator + fileName;
                part.write(filePath);
            }

            if (filePath != null) {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");

                File file = new File(filePath);

                String uniqueName = imagesService.getLastId() + 1 + "." + FilenameUtils.getExtension(fileName);

                user.setFilePath(File.separator + "Cinema-1.0-SNAPSHOT" + File.separator + "images" + File.separator + uniqueName);

                imagesService.save(user, fileName, getSize(file), getMime(file), uniqueName);

                user.setImages(imagesService.findByUser(user));

                String newFilePath = savePath + File.separator + uniqueName;

                renameFile(file, newFilePath);

                request.setAttribute("user", user);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(request, response);
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

    private void renameFile(File file, String filePath) throws IOException {
        File file2 = new File(filePath);

        if (file2.exists())
            throw new java.io.IOException("file exists");

        if (file.renameTo(file2)) {
            System.out.println("Directory renamed successfully");
        } else {
            System.out.println("Failed to rename directory");
        }
    }
}

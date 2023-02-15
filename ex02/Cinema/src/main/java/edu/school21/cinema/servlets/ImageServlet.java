package edu.school21.cinema.servlets;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/jpeg");
        String storagePath = getStoragePath();
        String uri = request.getRequestURI();
        String uniqueName = uri.substring(uri.lastIndexOf('/') + 1);
        FileInputStream fis = new FileInputStream(storagePath + File.separator + uniqueName);
        IOUtils.copy(fis, response.getOutputStream());
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
}

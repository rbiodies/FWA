package edu.school21.cinema.services;

import edu.school21.cinema.models.Image;
import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImagesServiceImpl implements ImagesService {
    ImagesRepository imagesRepository;

    @Autowired
    public ImagesServiceImpl(@Qualifier("imagesRepositoryJdbcTemplateImpl") ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    @Override
    public void save(User user, String fileName, String size, String mime, String uniqueName) {
        Image image = new Image(1L, user, fileName, size, mime, uniqueName);

        imagesRepository.save(image);
    }

    @Override
    public List<Image> findByUser(User user) {
        return imagesRepository.findById(user.getId());
    }

    @Override
    public Image getLastImageByUser(User user) {
        List<Image> images = imagesRepository.findById(user.getId());
        if (images.size() > 0) {
            return images.get(images.size() - 1);
        }
        return null;
    }

    @Override
    public int getCountImages() {
        List<Image> images = imagesRepository.findAll();
        return images.size();
    }
}

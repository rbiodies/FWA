package edu.school21.cinema.services;

import edu.school21.cinema.models.Images;
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
        Images images = new Images(1L, user, fileName, size, mime, uniqueName);

        imagesRepository.save(images);
    }

    @Override
    public List<Images> findByUser(User user) {
        return imagesRepository.findById(user.getId());
    }

    @Override
    public int getLastId() {
        return imagesRepository.findAll().size();
    }
}

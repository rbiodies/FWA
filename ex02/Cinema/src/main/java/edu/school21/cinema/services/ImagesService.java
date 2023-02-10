package edu.school21.cinema.services;

import edu.school21.cinema.models.Images;
import edu.school21.cinema.models.User;

import java.util.List;

public interface ImagesService {

    void save(User user, String fileName, String size, String mime, String uniqueName);

    List<Images> findByUser(User user);

    int getLastId();
}

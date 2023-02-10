package edu.school21.cinema.services;

import edu.school21.cinema.models.Data;
import edu.school21.cinema.models.User;

import java.util.List;

public interface DataService {

    void save(User user, String date, String time, String ip);

    List<Data> findByUser(User user);
}

package edu.school21.cinema.services;

import edu.school21.cinema.models.Data;
import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataServiceImpl implements DataService {
    DataRepository dataRepository;

    @Autowired
    public DataServiceImpl(@Qualifier("dataRepositoryJdbcTemplateImpl") DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public DataServiceImpl() {
    }

    @Override
    public void save(User user, String date, String time, String ip) {
        Data data = new Data(1L, user, date, time, ip);

        dataRepository.save(data);
    }

    @Override
    public List<Data> findByUser(User user) {
        return dataRepository.findById(user.getId());
    }
}

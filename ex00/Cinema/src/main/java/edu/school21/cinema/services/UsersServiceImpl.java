package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsersServiceImpl implements UsersService {
    UsersRepository usersRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UsersServiceImpl(@Qualifier("usersRepositoryJdbcTemplateImpl") UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UsersServiceImpl() {
    }

    @Override
    public void signUp(String firstName, String lastName, String phoneNumber, String email, String password) {
        String bCryptedPassword = bCryptPasswordEncoder.encode(password);
        User user = new User(1L, firstName, lastName, phoneNumber, email, bCryptedPassword);

        usersRepository.save(user);
    }

    @Override
    public Optional<User> signIn(String email, String password) {
        Optional<User> user = usersRepository.findByEmail(email);
        if (user.isPresent()) {
            boolean passwordIsValid = bCryptPasswordEncoder.matches(password, user.get().getPassword());
            if (passwordIsValid) {
                return user;
            }
        }
        return Optional.empty();
    }
}

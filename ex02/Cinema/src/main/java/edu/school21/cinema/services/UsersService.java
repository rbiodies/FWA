package edu.school21.cinema.services;

import edu.school21.cinema.models.User;

import java.util.Optional;

public interface UsersService {

    void signUp(String firstName, String lastName, String phoneNumber, String email, String password);

    Optional<User> signIn(String email, String password);

    void saveAvatar(Long id, String pathToAvatar);
}

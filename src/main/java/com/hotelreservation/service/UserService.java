package com.hotelreservation.service;

import com.hotelreservation.model.User;
import com.hotelreservation.repository.UserRepository;

public class UserService {

    private final UserRepository userRepository = new UserRepository();

    public boolean registerUser(User user) {
        if (user == null) return false;
        if (user.getUsername() == null || user.getUsername().isEmpty()) return false;
        if (user.getPassword() == null || user.getPassword().isEmpty()) return false;

        return userRepository.saveUser(user);
    }

    public User authenticate(String username, String password) {

        if (username == null || password == null) {
            return null;
        }

        User user = userRepository.findByUsername(username);

        if (user == null) {
            return null; // user not found
        }

        // password check
        if (!password.equals(user.getPassword())) {
            return null; // wrong password
        }

        // status check (VERY IMPORTANT)
        if (!"ACTIVE".equalsIgnoreCase(user.getStatus())) {
            return null; // inactive or blocked user
        }

        return user; // authentication success
    }
}

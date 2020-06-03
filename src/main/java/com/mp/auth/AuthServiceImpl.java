package com.mp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    AuthRepository authRepository;

    public static String currentUser = "";

    public static void setCurrentUser(String currentUser) {
        AuthServiceImpl.currentUser = currentUser;
    }

    public static String getCurrentUser() {
        return currentUser;
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return authRepository.findById(username);
    }

    @Override
    public User save(User user) {
        return authRepository.save(user);
    }
}

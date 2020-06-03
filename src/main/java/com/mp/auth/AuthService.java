package com.mp.auth;

import java.util.Optional;

public interface AuthService {
    Optional<User> getByUsername(String username);
    User save(User user);
}

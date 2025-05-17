package org.reuac.yrt_001.service;

import org.reuac.yrt_001.model.User;

import java.util.Optional;

public interface UserService {
    boolean registerUser(String username, String password, String email);

    Optional<User> loginUser(String username, String password);

    boolean usernameExists(String username);

    boolean emailExists(String email);
}
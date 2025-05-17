package org.reuac.yrt_001.dao;

import org.reuac.yrt_001.model.User;

import java.util.Optional;

public interface UserDAO {
    boolean createUser(User user);

    Optional<User> findByUsername(String username);

    boolean usernameExists(String username);

    boolean emailExists(String email);
}
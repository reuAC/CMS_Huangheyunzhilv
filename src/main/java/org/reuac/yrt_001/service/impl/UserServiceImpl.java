package org.reuac.yrt_001.service.impl;

import org.reuac.yrt_001.dao.UserDAO;
import org.reuac.yrt_001.dao.impl.UserDAOImpl;
import org.reuac.yrt_001.model.User;
import org.reuac.yrt_001.service.UserService;
import org.reuac.yrt_001.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean registerUser(String username, String password, String email) {
        if (usernameExists(username)) {
            logger.warn("Registration attempt with existing username: {}", username);
            return false;
        }
        if (email != null && !email.trim().isEmpty() && emailExists(email)) {
            logger.warn("Registration attempt with existing email: {}", email);
            return false;
        }

        String salt = SecurityUtil.generateSalt();
        String passwordHash = SecurityUtil.hashPassword(password, salt);

        User newUser = new User(username, passwordHash, salt, email);
        return userDAO.createUser(newUser);
    }

    @Override
    public Optional<User> loginUser(String username, String password) {
        Optional<User> userOpt = userDAO.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (SecurityUtil.verifyPassword(password, user.getPasswordHash(), user.getSalt())) {
                return Optional.of(user);
            } else {
                logger.warn("Failed login attempt for username: {} (incorrect password)", username);
            }
        } else {
            logger.warn("Failed login attempt for username: {} (user not found)", username);
        }
        return Optional.empty();
    }

    @Override
    public boolean usernameExists(String username) {
        return userDAO.usernameExists(username);
    }

    @Override
    public boolean emailExists(String email) {
        return userDAO.emailExists(email);
    }
}
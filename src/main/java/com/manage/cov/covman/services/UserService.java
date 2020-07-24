package com.manage.cov.covman.services;

import com.manage.cov.covman.entity.User;
import com.manage.cov.covman.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     *
     * @param email
     * @param password
     * @return User
     */
    public User getUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }


}

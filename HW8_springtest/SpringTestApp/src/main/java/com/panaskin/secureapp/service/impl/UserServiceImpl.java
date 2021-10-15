package com.panaskin.secureapp.service.impl;

import com.panaskin.secureapp.entity.User;
import com.panaskin.secureapp.repository.UserRepository;
import com.panaskin.secureapp.service.EntityService;
import com.panaskin.secureapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User save(User entity) {
        userRepository.save(entity);
        return entity;
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
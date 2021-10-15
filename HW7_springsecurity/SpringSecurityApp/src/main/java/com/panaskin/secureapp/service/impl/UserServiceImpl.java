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
    public List<User> receiveAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> receiveById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void add(User session) {
        userRepository.save(session);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
package com.panaskin.secureapp.service;

import com.panaskin.secureapp.entity.User;

public interface UserService extends EntityService<User>{
    User findByLogin(String login);
}

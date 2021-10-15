
package com.panaskin.hibernapp.service;

import com.panaskin.hibernapp.dto.UserDto;
import com.panaskin.hibernapp.entity.User;

public interface UserAssembler {
    User assemble(UserDto dto);
    UserDto assemble(User entity);
}


package com.pigorv.springcloud.orders.clients;

import com.pigorv.springcloud.orders.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "users", fallback = UserClient.UserClientImpl.class)
public interface UserClient {
    @GetMapping(value = "/{userName}")
    UserDto getUser(@PathVariable("userName") String userName);

    @Component
    class UserClientImpl implements UserClient {
        @Override
        public UserDto getUser(String userName) {
            return null;
        }
    }
}

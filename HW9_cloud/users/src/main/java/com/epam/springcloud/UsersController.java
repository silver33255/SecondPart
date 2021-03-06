package com.epam.springcloud;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private Map<String, User> registeredUsers = new HashMap<>();

    private final OrderClient orderClient;

    @PostMapping
    public User createUser() {
        var user = new User(RandomStringUtils.randomAlphabetic(13));
        registeredUsers.put(user.getName(), user);

        return user;
    }

    @GetMapping("/{userName}")
    public ResponseEntity<User> getUser(@PathVariable String userName) {
        User user = registeredUsers.get(userName);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping()
    public List getAllUsers() {
        return new ArrayList(registeredUsers.values());
    }

    @GetMapping("/{userName}/products")
    public List getProductsByUser(@PathVariable String userName) {
        return orderClient.getProducts(userName);
    }

    private ArrayList<String> getDefaultProducts(String value) {
        return new ArrayList<>(List.of("One", "Two", "Three"));
    }
}

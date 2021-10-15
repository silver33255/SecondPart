package com.epam.springcloud.notification;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class NotificationController {
    private final Set<Notification> notifications = new HashSet<>();

    @PostMapping()
    public Notification notify(@RequestParam("userName") String userName) {
        if (userName==null) userName="123";
        Notification n=new Notification();
        n.setUser(userName);
        notifications.add(n);
        return n;
    }

    @GetMapping
    public List<Notification> getNotifications() {
        return new ArrayList<>(notifications);
    }
}

package com.pigorv.springcloud.orders.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "notifications", fallback = NotificationClient.NotificationClientImpl.class)
public interface NotificationClient {
    @PostMapping
    String addNotifier(@RequestParam("userName") String userName);

    @Component
    class NotificationClientImpl implements NotificationClient {
        @Override
        public String addNotifier(String userName) {
            return null;
        }
    }
}

package com.pigorv.springcloud.orders;

import com.pigorv.springcloud.orders.clients.NotificationClient;
import com.pigorv.springcloud.orders.clients.ProductClient;
import com.pigorv.springcloud.orders.clients.UserClient;
import com.pigorv.springcloud.orders.dto.ProductDto;
import com.pigorv.springcloud.orders.dto.UserDto;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequestMapping
@RestController
@RequiredArgsConstructor
public class OrdersController {
    private List<Order> orderList = new ArrayList<>();

    private final UserClient userClient;
    private final ProductClient productClient;
    private final NotificationClient notificationClient;

    @GetMapping
    public String health() {
        return "OK";
    }

    @PostMapping
    public ResponseEntity<Order> createNewOrder(@RequestBody Order order) {
        UserDto user;
        try {
            user = userClient.getUser(order.getUserName());
        } catch (FeignException.FeignClientException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ProductDto product;
        try {
            product = productClient.removeOneProduct(order.getProduct());
        } catch (FeignException.FeignClientException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (user == null || product == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        orderList.add(order);
        notificationClient.addNotifier(order.getUserName());
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/users/{userName}")
    public List<String> getProductsForUser(@PathVariable String userName) {
        return orderList.stream()
                .filter(order -> userName.equals(order.getUserName()))
                .map(Order::getProduct)
                .collect(toList());
    }

    @Bean
    private RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}

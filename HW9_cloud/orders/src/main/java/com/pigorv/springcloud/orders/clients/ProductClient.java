package com.pigorv.springcloud.orders.clients;

import com.pigorv.springcloud.orders.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(value = "products",fallback = ProductClient.ProductClientImpl.class)
public interface ProductClient {
    @PutMapping(value="/{productName}")
    ProductDto removeOneProduct(@PathVariable String productName);

    @Component
    class ProductClientImpl implements ProductClient {
        @Override
        public ProductDto removeOneProduct(String productName) {
            return null;
        }
    }
}

package com.order.config;


import com.order.entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service", url = "http://localhost:9090/products")
public interface ProductClient {

    @GetMapping("/{id}")
    Product getProductById(@PathVariable("id") int id);

    @PostMapping("/addProduct")
    Product addProduct(@RequestBody Product product);
}

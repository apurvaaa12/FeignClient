package com.order.controller;


import com.order.config.ProductClient;
import com.order.entities.Order;
import com.order.entities.Product;
import com.order.repo.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    public OrderController(OrderRepository orderRepository, ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Product product = productClient.getProductById(order.getProductId());
        if (product != null && product.getStock() >= order.getQuantity()) {
            orderRepository.save(order);
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/get")
    public ResponseEntity<Product> getProduct(@RequestBody Order order){
        Product product = productClient.getProductById(order.getProductId());
        return ResponseEntity.ok(product);
    }
}

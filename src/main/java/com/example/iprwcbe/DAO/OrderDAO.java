package com.example.iprwcbe.DAO;

import com.example.iprwcbe.model.database.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderDAO {
    @Autowired
    private final OrderRepository orderRepository;

    public OrderDAO(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAll() {return this.orderRepository.findAll();}
    public Optional<Order> getById(String id) { return this.orderRepository.findById(id);}
    public Order save(Order order){return this.orderRepository.save(order);}
    public List<Order> getOrdersByUserId(String userId) {return this.orderRepository.findOrdersByUserId(userId);}
    public void deleteById(String id) { this.orderRepository.deleteById(id);}
}

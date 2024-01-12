package com.example.iprwcbe.DAO;

import com.example.iprwcbe.model.database.Order;
import com.example.iprwcbe.model.database.OrderProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderProductsDAO {
    @Autowired
    private final OrderProductsRepository orderProductsRepository;

    public OrderProductsDAO(OrderProductsRepository orderProductsRepository) {
        this.orderProductsRepository = orderProductsRepository;
    }

    public List<OrderProduct> getAll() {return this.orderProductsRepository.findAll();}
    public List<OrderProduct> findAllByOrder(Order order) {return this.orderProductsRepository.findAllByOrderIs(order);}
}

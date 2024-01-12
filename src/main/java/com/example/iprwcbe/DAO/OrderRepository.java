package com.example.iprwcbe.DAO;

import com.example.iprwcbe.model.database.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findOrdersByUserId(String userId);
}

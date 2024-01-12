package com.example.iprwcbe.DAO;

import com.example.iprwcbe.model.database.Order;
import com.example.iprwcbe.model.database.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductsRepository extends JpaRepository<OrderProduct, String> {
    List<OrderProduct> findAllByOrderIs(Order order);
}

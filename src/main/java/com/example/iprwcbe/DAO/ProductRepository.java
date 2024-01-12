package com.example.iprwcbe.DAO;

import com.example.iprwcbe.model.database.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> getAllByStockQuantityIsGreaterThan(int stock);


}

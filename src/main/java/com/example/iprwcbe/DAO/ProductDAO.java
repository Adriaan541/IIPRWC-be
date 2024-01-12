package com.example.iprwcbe.DAO;

import com.example.iprwcbe.model.database.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductDAO {
    @Autowired
    private final ProductRepository productRepository;

    public ProductDAO(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() { return this.productRepository.findAll();}
    public Optional<Product> getById(String id) { return this.productRepository.findById(id);}
    public List<Product> getInStock() { return this.productRepository.getAllByStockQuantityIsGreaterThan(0);}
    public Product save(Product product){return this.productRepository.save(product);}
    public void deleteById(String id){
        this.productRepository.deleteById(id);
    }
}

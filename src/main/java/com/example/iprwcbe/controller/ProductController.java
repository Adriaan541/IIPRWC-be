package com.example.iprwcbe.controller;

import com.example.iprwcbe.DAO.ProductDAO;
import com.example.iprwcbe.exceptions.RecordNotFoundException;
import com.example.iprwcbe.model.database.Product;
import com.example.iprwcbe.model.database.User;
import com.example.iprwcbe.model.http.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductDAO productDAO;

    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok().body(productDAO.getInStock());
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Product>> getAllProducts(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(productDAO.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductbyId(@PathVariable("id") String id) {
        Product product = productDAO.getById(id).orElseThrow(()-> new RecordNotFoundException("Location with id: " + id + " not found"));

        try {
            return ResponseEntity.ok().body(product);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createProduct(@RequestBody Product product){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(productDAO.save(product));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Invalid data", List.of()));
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @Valid @RequestBody Product product) {
        try {
            product.setId(id);
            return ResponseEntity.ok(productDAO.save(product));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Invalid data", List.of()));
        }
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        try {
            productDAO.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

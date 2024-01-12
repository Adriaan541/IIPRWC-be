package com.example.iprwcbe.DAO;

import com.example.iprwcbe.model.database.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    List<Category> findDistinctByProductsIsNotNull();
}
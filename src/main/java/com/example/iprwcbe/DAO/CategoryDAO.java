package com.example.iprwcbe.DAO;

import com.example.iprwcbe.model.database.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryDAO {
    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryDAO(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public List<Category> getAll() {return this.categoryRepository.findAll();}
    public Optional<Category> getById(String id) {return this.categoryRepository.findById(id);}
    public List<Category> getNonEmptyCategories() {return this.categoryRepository.findDistinctByProductsIsNotNull();}
    public Category save(Category category){return this.categoryRepository.save(category);}
    public void deleteById(String id) {this.categoryRepository.deleteById(id);}
}

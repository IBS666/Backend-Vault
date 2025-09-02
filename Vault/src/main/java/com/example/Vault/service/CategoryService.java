package com.example.Vault.service;

import com.example.Vault.model.Category;
import com.example.Vault.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(String catName) {
        if (categoryRepository.existsByCatName(catName)) {
            throw new RuntimeException("Category already exists");
        }

        Category category = new Category(catName);
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategory(Long catId) {
        return categoryRepository.findById(catId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Category getCategoryByName(String catName) {
        return categoryRepository.findByCatName(catName)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Category updateCategory(Long catId, String newName) {
        Category category = getCategory(catId);

        if (!category.getCatName().equals(newName) && categoryRepository.existsByCatName(newName)) {
            throw new RuntimeException("Category name already exists");
        }

        category.setCatName(newName);
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long catId) {
        if (!categoryRepository.existsById(catId)) {
            throw new RuntimeException("Category not found");
        }
        categoryRepository.deleteById(catId);
    }
}
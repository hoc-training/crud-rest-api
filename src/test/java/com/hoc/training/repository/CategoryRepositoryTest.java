package com.hoc.training.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hoc.training.entity.Category;

@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void insertCategory() {
        {
            Category category = new Category();
            category.setName("Category 1");
            categoryRepository.save(category);
        }

        {
            Category category = new Category();
            category.setName("Category 2");
            categoryRepository.save(category);
        }
    }
}

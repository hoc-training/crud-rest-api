package com.hoc.training.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hoc.training.entity.Category;
import com.hoc.training.entity.SubCategory;

@SpringBootTest
public class SubCategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Test
    void insertTwoTables() {
        Category cat = new Category();
        cat.setName("Category 10");
        categoryRepository.save(cat);

        Category c = categoryRepository.findFirstByNameEquals(cat.getName()).orElse(null);
        assertNotNull(c);
        assertEquals("Category 10", c.getName());

        SubCategory subCategory = new SubCategory();
        subCategory.setCategory(cat);
        subCategory.setName("Sub Category 5");
        subCategoryRepository.save(subCategory);

        SubCategory sc = subCategoryRepository.findFirstByNameEquals(subCategory.getName()).orElse(null);
        assertNotNull(sc);
        assertEquals("Sub Category 5", sc.getName());
    }
}

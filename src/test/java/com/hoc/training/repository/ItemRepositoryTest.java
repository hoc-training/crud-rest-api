package com.hoc.training.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hoc.training.entity.Category;
import com.hoc.training.entity.Item;

@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void insertItem() {
        Category category = categoryRepository.findById(1L).orElse(null);
        assertNotNull(category);

        {
            Item item = new Item();
            item.setCategory(category);
            item.setName("Book");
            item.setPrice(100_000L);
            itemRepository.save(item);
        }

        {
            Item item = new Item();
            item.setCategory(category);
            item.setName("Phone");
            item.setPrice(1_000_000L);
            itemRepository.save(item);
        }
    }

    @Test
    void findByCategoryName() {
        List<Item> items = itemRepository.findAllItemByCategory_Name("Category 1");
        assertEquals(8, items.size());
        assertEquals("Book", items.get(0).getName());
        assertEquals("Phone", items.get(1).getName());
    }
}

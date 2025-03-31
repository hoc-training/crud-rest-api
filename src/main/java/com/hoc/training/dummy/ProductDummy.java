package com.hoc.training.dummy;

import java.util.ArrayList;
import java.util.List;

import com.hoc.training.entity.Product;

public class ProductDummy {

    private List<Product> products = new ArrayList<>();
    private Product product = new Product();

    public Product getProductById(Long id) {
        product = new Product();
        product.setDescription("Deskripsi produk");
        product.setId(1L);
        product.setName("Nama Produk");
        product.setPrice(75000D);

        return product;
    }

    public List<Product> getProducts() {
        for (int i = 1; i < 10; i++) {
            product = new Product();
            product.setDescription("Deskripsi produk " + i);
            product.setId(Long.valueOf(i));
            product.setName("Nama Produk" + i);
            product.setPrice(75000D + Double.valueOf(i));
            products.add(product);
        }

        return products;
    }

    public void addProductDummy(Product product) {
        products.add(product);
    }
}

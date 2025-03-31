package com.hoc.training.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoc.training.dummy.ProductDummy;
import com.hoc.training.entity.Product;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private List<Product> products = new ArrayList<>();
    private ProductDummy dummy = new ProductDummy();

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = new ProductDummy().getProductById(id);
        if (product.getId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = new ProductDummy().getProducts();
        if (products.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return new ProductDummy().getProducts();
    }

    /**
     * get data from private list
     * 
     * @param product
     * @return
     */
    @PostMapping()
    public ResponseEntity<List<Product>> addProduct(@RequestBody Product product) {
        products.add(product);

        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ProductDummy> addDummy(@RequestBody Product product) {
        dummy.addProductDummy(product);

        return new ResponseEntity<ProductDummy>(dummy, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<Product>> editDummy(@PathVariable Long id, @RequestBody Product product) {
        List<Product> products = new ProductDummy().getProducts();
        for (Product tmp : products) {
            if (tmp.getId() == id) {
                products.remove(tmp);
                tmp.setDescription("Ubah deskirpsi");
                tmp.setName("Ubah nama");
                tmp.setPrice(50000D);
                products.add(tmp);
                break;
            }
        }

        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Product>> deleteDummy(@PathVariable Long id) {
        Product dataRemoved = null;
        List<Product> tmp = new ProductDummy().getProducts();
        for (Product product : tmp) {
            if (product.getId() == id) {
                dataRemoved = product;
                break;
            }
        }

        if (dataRemoved == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        tmp.remove(dataRemoved);

        return new ResponseEntity<List<Product>>(tmp, HttpStatus.OK);
    }
}

package com.spriti.controller;

import com.spriti.Model.Product;
import com.spriti.service.CartService;
import com.spriti.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @PostMapping("/admin/saveProduct")
    public ResponseEntity<Product> saveNewProd(@RequestBody Product product){

        Product pro = productService.saveProduct(product);

        return new ResponseEntity<>(pro, HttpStatus.CREATED);
    }

    @PatchMapping("/admin/updatePrice/{id}")
    public Product updatePrice(@PathVariable("id") int id,
                               @RequestParam double price){
        Product product = productService.updateProductPrice(id, price);

        return product;
    }

    @PatchMapping("/admin/updateStock/{id}")
    public Product updateStock(@PathVariable("id") int id,
                               @RequestParam int quantity){
        Product product = productService.updateProductStock(id, quantity);

        return product;
    }

    @DeleteMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") int id){

        return productService.deleteById(id);
    }


    @GetMapping("/product/{id}")
    public Product getSingleProduct(@PathVariable int id){
        Product product = productService.getProductById(id);

        return product;
    }

    @GetMapping("/prod/{category}")
    public List<Product> getProByCategory(@PathVariable String category){
        List<Product> products = productService.getProductByCategory(category);

        return products;
    }

    @GetMapping("/showAll")
    public List<Product> getAllProducts(){
        List<Product> productList = productService.showAllProducts();

        return productList;
    }
}

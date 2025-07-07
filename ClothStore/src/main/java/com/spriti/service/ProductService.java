package com.spriti.service;

import com.spriti.Model.Product;

import java.util.List;

public interface ProductService {

    public Product saveProduct(Product product);

    public Product getProductById(int id);

    public List<Product> getProductByCategory(String category);

    public List<Product> showAllProducts();

    public Product updateProductPrice(int id, double price);

    public Product updateProductStock(int id, int quantity);

    public String deleteById(int id);
}

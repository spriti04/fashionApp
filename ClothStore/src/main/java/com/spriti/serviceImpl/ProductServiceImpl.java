package com.spriti.serviceImpl;

import com.spriti.Model.Product;
import com.spriti.repository.ProductRepository;
import com.spriti.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
       return productRepository.save(product);
    }

    @Override
    public Product getProductById(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return product;
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        List<Product> products = productRepository.findByCategory(category);

        return products;
    }

    @Override
    public List<Product> showAllProducts() {
        List<Product> productList = productRepository.findAll();

        return productList;
    }

    @Override
    public Product updateProductPrice(int id, double price) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setPrice(price);
        return productRepository.save(product);
    }

    @Override
    public Product updateProductStock(int id, int quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setStock(product.getStock() + quantity);
        return productRepository.save(product);
    }

    @Override
    public String deleteById(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));


        productRepository.delete(product);
        return "Product deleted successfully";

    }
}

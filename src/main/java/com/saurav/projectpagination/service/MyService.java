package com.saurav.projectpagination.service;

import com.saurav.projectpagination.entity.Product;
import com.saurav.projectpagination.repository.ProductRepository;
//import jakarta.annotation.PostConstruct;
import com.saurav.projectpagination.response.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.rangeClosed;

@Service
public class MyService {

    @Autowired
    private ProductRepository productRepository;

//    @PostConstruct
//    public void initDB() {
//        Random random = new Random();
//
//        List<Product> products = IntStream.rangeClosed(1, 200)
//                .mapToObj(i -> {
//                    int qty = random.nextInt(100);            // 0–99
//                    long price = 100L + random.nextInt(9900); // 100–9999
//                    // Use constructor matching (String, int, long)
//                    return new Product("Product" + i, qty, price);
//                })
//                .collect(Collectors.toList());
//
//        productRepository.saveAll(products);
//    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductWithSorting(String field){
       return productRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    public Page<Product> getProductWithPagination(int offset, int pageSize){
        Page<Product> productsWithPagination = productRepository.findAll(PageRequest.of(offset, pageSize));
        return productsWithPagination;
    }

    public Page<Product> getProductWithPaginationAndSorting(int offset, int pageSize, String field){
        Page<Product> products = productRepository.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.ASC, field)));
        return products;
    }

}
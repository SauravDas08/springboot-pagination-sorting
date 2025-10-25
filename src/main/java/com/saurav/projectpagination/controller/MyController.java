package com.saurav.projectpagination.controller;

import com.saurav.projectpagination.entity.Product;
import com.saurav.projectpagination.response.APIResponse;
import com.saurav.projectpagination.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/products")
public class MyController {

    @Autowired
    private MyService myService;

    @GetMapping(produces = "application/json")
    public APIResponse<List<Product>> getAllProducts() {
        List<Product> allProducts = myService.getAllProducts();
        APIResponse<List<Product>> data = APIResponse.<List<Product>>builder().recordCount(allProducts.size())
                .message("Fetched Successfully")
                .response(allProducts)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();

        return data;
    }

    @GetMapping("/{field}")
    public APIResponse<List<Product>> getProductsWithSorting(@PathVariable String field){
        List<Product> sorted = myService.getProductWithSorting(field);
        APIResponse<List<Product>> data = APIResponse.<List<Product>>builder()
                .response(sorted)
                .recordCount(sorted.size())
                .message("Succesfully fetched")
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return data;
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public APIResponse<Page<Product>> getProductWithPagination(@PathVariable int offset ,@PathVariable int pageSize){
        Page<Product> productWithPagination = myService.getProductWithPagination(offset, pageSize);

        APIResponse<Page<Product>> data = APIResponse.<Page<Product>>builder()
                .response(productWithPagination)
                .recordCount(productWithPagination.getSize())
                .message("Success")
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return data;

    }

    @GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
    public Page<Product> getProductWithPaginationAndSorting(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String field) {
        Page<Product> products = myService.getProductWithPaginationAndSorting(offset, pageSize, field);

    APIResponse<Page<Product>> data = APIResponse.<Page<Product>>builder()
            .response(products)
            .message("Success")
            .recordCount(pageSize)
            .status(HttpStatus.OK)
            .timestamp(LocalDateTime.now())
            .build();
    return data.getResponse();

    }
}

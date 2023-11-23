package com.industries.lunar.rest.controller;

import com.industries.lunar.rest.model.Product;
import com.industries.lunar.rest.model.dto.ProductResponseDTO;
import com.industries.lunar.rest.repository.ProductRepository;
import com.industries.lunar.rest.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/products")
public class MarketAPIController {

    @Autowired
    ProductService productService;

    @GetMapping("")
    public Page<ProductResponseDTO> getProducts()
    {
        log.info("Searching for all products");
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> products = productService.getProducts(pageable);

        log.info("Products found!");
        return ProductResponseDTO.toDTO(products);
    }

    @GetMapping("{productId}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable(name = "productId") long productId)
    {
        Product product = Product.builder().id(productId).name("ProductName").description("description").build();
        return new ResponseEntity<>(new ProductResponseDTO(product), HttpStatus.OK);
    }

}

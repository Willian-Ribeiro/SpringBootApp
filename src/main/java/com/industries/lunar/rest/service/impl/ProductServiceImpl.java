package com.industries.lunar.rest.service.impl;

import com.industries.lunar.rest.model.Product;
import com.industries.lunar.rest.repository.ProductRepository;
import com.industries.lunar.rest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products;
    }
}

package com.industries.lunar.rest.service;

import com.industries.lunar.rest.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface ProductService {

    Page<Product> getProducts(Pageable pageable);

}

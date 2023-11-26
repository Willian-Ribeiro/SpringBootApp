package com.industries.lunar.rest.service;

import com.industries.lunar.rest.model.Product;
import com.industries.lunar.rest.model.dto.ProductRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.NoSuchElementException;

public interface ProductService {

    Page<Product> getProducts(ProductRequestDTO requestDTO,Pageable pageable);

    Product getProductById(long productId) throws NoSuchElementException;

    Product createProduct(ProductRequestDTO productRequestDTO);

    Product updateProduct(long productId, ProductRequestDTO productRequestDTO) throws NoSuchElementException;

}

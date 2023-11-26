package com.industries.lunar.rest.service.impl;

import com.industries.lunar.rest.model.Product;
import com.industries.lunar.rest.model.dao.ProductSearch;
import com.industries.lunar.rest.model.dto.ProductRequestDTO;
import com.industries.lunar.rest.repository.ProductRepository;
import com.industries.lunar.rest.service.ProductService;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSearch productSearch;

    @Override
    public Page<Product> getProducts(ProductRequestDTO requestDTO,Pageable pageable) {
        List<Product> products = productSearch.searchByCriteria(requestDTO);
        for(Product product : products)
        {
            Hibernate.initialize(product.getLabels());
            Hibernate.initialize(product.getAPackage());
        }

        return new PageImpl<>(products, pageable, products.size());
    }

    @Override
    public Product getProductById(long productId) throws NoSuchElementException {
        Optional<Product> optional = productRepository.findById(productId);

        if(optional.isEmpty())
            throw new NoSuchElementException("Product with ID " + productId + "was NOT found");

        return optional.get();
    }

    @Override
    public Product createProduct(ProductRequestDTO productRequestDTO) {
        return productRepository.save(productRequestDTO.toProduct());
    }

    @Override
    public Product updateProduct(long productId, ProductRequestDTO productRequestDTO) throws NoSuchElementException{
        Product entity = getProductById(productId);

        entity.setName(productRequestDTO.getName());
        entity.setQuantityStock(productRequestDTO.getQuantityStock());
        entity.setDescription(productRequestDTO.getDescription());

        return entity;
    }
}

package com.industries.lunar.rest.controller;

import com.industries.lunar.rest.enums.DeliveryStatusEnum;
import com.industries.lunar.rest.model.Product;
import com.industries.lunar.rest.model.dto.ProductRequestDTO;
import com.industries.lunar.rest.model.dto.ProductResponseDTO;
import com.industries.lunar.rest.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.NoSuchElementException;

@Log4j2
@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    ProductService productService;

    @GetMapping("")
    public Page<ProductResponseDTO> getProducts(
            @RequestParam(value = "_pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "_pageSize", defaultValue = "20", required = false) Integer pageSize,
            @RequestParam(value = "deliveryStatus", required = false) DeliveryStatusEnum deliveryStatus)
    {
        log.info("Searching for all products");

        ProductRequestDTO requestDTO = ProductRequestDTO.builder().deliveryStatus(deliveryStatus).build();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Product> products = productService.getProducts(requestDTO, pageable);

        log.info("Products found");
        return ProductResponseDTO.toDTO(products);
    }

    @GetMapping("{productId}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable(name = "productId") long productId)
    {
        log.info("Searching for product with id {}", productId);

        try
        {
            Product product = productService.getProductById(productId);

            log.info("Found product with id {} ", productId);
            return ResponseEntity.ok(new ProductResponseDTO(product));
        }
        catch (NoSuchElementException e)
        {
            log.info("Product with id {} NOT found", productId);
            log.error(e.getMessage());
            log.error(e);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO)
    {
        log.info("Attempting to create product");
        Product product = productService.createProduct(productRequestDTO);
        URI locationResource = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("").buildAndExpand(product.getId()).toUri();

        log.info("Product created successfully");
        return ResponseEntity.created(locationResource).body(new ProductResponseDTO(product));
    }

    @PutMapping("{productId}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable(name = "productId") long productId,
            @RequestBody ProductRequestDTO productRequestDTO)
    {
        log.info("Attempting to update product");
        try
        {
            Product product = productService.updateProduct(productId, productRequestDTO);

            log.info("Product with id {} updated successfully", productId);
            return ResponseEntity.ok(new ProductResponseDTO(product));
        }
        catch (NoSuchElementException e)
        {
            log.info("Product with id {} NOT found", productId);
            log.error(e.getMessage());
            log.error(e);
            return ResponseEntity.notFound().build();
        }
    }
}

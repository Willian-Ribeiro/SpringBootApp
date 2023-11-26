package com.industries.lunar.rest.api.client;

import com.industries.lunar.rest.enums.DeliveryStatusEnum;
import com.industries.lunar.rest.model.dto.ProductRequestDTO;
import com.industries.lunar.rest.model.dto.ProductResponseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "cucumber-products",
        url = "${server.host}" + ":${server.port}" + "${server.servlet.context-path}" + "/products"
)
public interface FeignClientInterface {

    @PostMapping("")
    ResponseEntity<ProductResponseDTO> createProduct(
            @RequestBody ProductRequestDTO productRequestDTO);

    @GetMapping("")
    Page<ProductResponseDTO> getProducts(
            @RequestParam(value = "deliveryStatus", required = false) DeliveryStatusEnum deliveryStatus,
            Pageable pageable);

    @GetMapping("{productId}")
    ResponseEntity<ProductResponseDTO> getProductById(
            @PathVariable(value = "productId") Long productId);

    @PutMapping("{productId}")
    ResponseEntity<ProductResponseDTO> putProduct(
            @PathVariable(value = "productId") Long productId,
            @RequestBody ProductRequestDTO productRequestDTO);
}

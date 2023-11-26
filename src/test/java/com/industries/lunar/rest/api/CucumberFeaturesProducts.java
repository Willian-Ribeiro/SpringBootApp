package com.industries.lunar.rest.api;

import com.industries.lunar.rest.api.client.FeignClientInterface;
import com.industries.lunar.rest.enums.DeliveryStatusEnum;
import com.industries.lunar.rest.model.dto.ProductRequestDTO;
import com.industries.lunar.rest.model.dto.ProductResponseDTO;
import feign.FeignException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CucumberFeaturesProducts {

    @Autowired
    private FeignClientInterface feignClient;

    private ResponseEntity<ProductResponseDTO> productResponse;

    private Page<ProductResponseDTO> productResponsePage;

    // POST()
    @Given("products make POST call with {string} and {string}")
    public void postProducts(String description, String deliveryStatus)
    {
        ProductRequestDTO productRequestDTO = ProductRequestDTO.builder()
                .description(description)
                .deliveryStatus(DeliveryStatusEnum.valueOf(deliveryStatus))
                .build();
        try {
            productResponse = feignClient.createProduct(productRequestDTO);
        }
        catch (FeignException e) {
            productResponse = ResponseEntity.status(e.status()).build();
        }
    }

    @Then("products receives POST status code {int} with data {string}")
    public void receivePostProducts(int status, String description)
    {
        assertThat(productResponse.getStatusCode().value(), is(status));
        if(productResponse.getStatusCode().value() == HttpStatus.CREATED.value())
        {
            assertThat(Objects.requireNonNull(productResponse.getBody()).getDescription(), is(description));
        }
    }

    // GET()
    @Given("products make GET call with {string}")
    public void getProducts(String deliveryStatus)
    {
        Pageable pageable = PageRequest.of(0, 20);
        try {
            if(deliveryStatus.equals("null"))
                productResponsePage = feignClient.getProducts(null, pageable);
            else
                productResponsePage = feignClient.getProducts(DeliveryStatusEnum.valueOf(deliveryStatus), pageable);
        }
        catch (FeignException e) {
            productResponsePage = Page.empty();
        }
    }

    @Then("products receives GET status code {int} and the amount is {int}")
    public void receiveGetProducts(int status, int size)
    {
        assertThat(productResponsePage.getContent().size(), is(size));
    }

    // GET({productId})
    @Given("products make GET call with ID {int}")
    public void getProductsById(int productId)
    {
        try {
            productResponse = feignClient.getProductById((long) productId);
        }
        catch (FeignException e) {
            productResponse = ResponseEntity.status(e.status()).build();
        }
    }

    @Then("products receives GET status code {int} with description {string}")
    public void receiveGetProductsById(int status, String description)
    {
        assertThat(productResponse.getStatusCode().value(), is(status));
        if(productResponse.getStatusCode().value() == HttpStatus.OK.value())
        {
            assertThat(Objects.requireNonNull(productResponse.getBody()).getDescription(), is(description));
        }
    }

    // GET({productId})
    @Given("products make PUT call with ID {int} and {string}")
    public void putProduct(int productId, String description)
    {
        ProductRequestDTO productRequestDTO = ProductRequestDTO.builder().description(description).build();
        try {
            productResponse = feignClient.putProduct((long) productId, productRequestDTO);
        }
        catch (FeignException e) {
            productResponse = ResponseEntity.status(e.status()).build();
        }
    }

    @Then("products receives PUT status code {int} with description {string}")
    public void receivePutProduct(int status, String description)
    {
        assertThat(productResponse.getStatusCode().value(), is(status));
        if(productResponse.getStatusCode().value() == HttpStatus.OK.value())
        {
            assertThat(Objects.requireNonNull(productResponse.getBody()).getDescription(), is(description));
        }
    }

}

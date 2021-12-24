package com.example.floor_myshop;

import com.example.floor_myshop.entity.Product;
import com.example.floor_myshop.model.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@SpringBootTest
public class ProductControllerTest {

    private RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "http://localhost:8080/";

    @Test
    public void testAddProduct(){
        final Product product = new Product();
        product.setProductName("玫瑰");
        product.setCategoryId(12);
        product.setShopId(12);
        final HttpEntity<Product> entity = new HttpEntity<>(product);
        final ResponseEntity<ApiResponse> response = restTemplate.exchange(BASE_URL + "product/addProduct", HttpMethod.POST, entity, ApiResponse.class);
        final ApiResponse<Product> body = response.getBody();
        System.out.println(body.getData());


    }

    @Test
    public void testGetProductList() {
        final ApiResponse<List<Product>> response = (ApiResponse<List<Product>>) restTemplate.getForObject(BASE_URL + "product/getProductList?category={category}&name={name}", ApiResponse.class, "玫瑰", "玫瑰");
        final List<Product> data = response.getData();
        System.out.println(data);
    }



}

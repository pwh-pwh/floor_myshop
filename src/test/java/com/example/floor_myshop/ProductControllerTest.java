package com.example.floor_myshop;

import com.example.floor_myshop.entity.Product;
import com.example.floor_myshop.service.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private IProductService productService;

    @Test
    public void testAddProduct(){
        final Product product = new Product();
        product.setProductName("玫瑰11");
        product.setCategoryId(13);
        product.setShopId(12);
        final boolean save = productService.save(product);
        System.out.println(product);

    }




}

package com.example.floor_myshop.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.floor_myshop.entity.Category;
import com.example.floor_myshop.entity.Product;
import com.example.floor_myshop.network.ApiResponse;
import com.example.floor_myshop.network.ResponseCode;
import com.example.floor_myshop.service.ICategoryService;
import com.example.floor_myshop.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coderpwh
 * @since 2021-12-19
 */
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @PostMapping("addProduct")
    public ApiResponse addProduct(@RequestBody final Product product){

        if (productService.save(product)) {
            final HashMap<String, Object> queryM = new HashMap<>() {{
                put("shop_id", product.getShopId());
                put("product_name", product.getProductName());
            }};
            final Product one = productService.getOne(Wrappers.<Product>query().allEq(queryM));
            return ApiResponse.success("添加商品成功",one);
        } else {
            return ApiResponse.failed(ResponseCode.PRODUCT_IS_EXISTS.getMessage(),ResponseCode.PRODUCT_IS_EXISTS.getCode());
        }
    }

    @GetMapping("getProductList")
    public ApiResponse<List<Product>> getProductList(
            @RequestParam("condition_type") String condition_type,
            @RequestParam("condition") String condition) {
        if ("keyword".equals(condition_type)) {
            final String name = condition;
            final List<Product> list = productService.list(
                    Wrappers.<Product>lambdaQuery().like(Product::getProductName, name));
            return ApiResponse.success("获取商品列表成功",list);
        } else if ("category".equals(condition_type)) {
            final String category = condition;
            if ("全部".equals(category)){
                final List<Product> list = productService.list();
                return ApiResponse.success("获取商品列表成功",list);
            } else {
                final Category categoryEntity = categoryService.getOne(
                        new QueryWrapper<Category>()
                                .lambda()
                                .eq(Category::getCategoryName, category));
                final List<Product> list = productService.list(
                        Wrappers.<Product>lambdaQuery()
                                .eq(Product::getCategoryId, categoryEntity.getCategoryId()));
                return ApiResponse.success("获取商品列表成功",list);
            }
        } else {
            return ApiResponse.failed("错误的值 condition_type="+condition_type+"");
        }
    }




}


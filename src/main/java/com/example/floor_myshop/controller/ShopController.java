package com.example.floor_myshop.controller;


import com.example.floor_myshop.entity.Shop;
import com.example.floor_myshop.network.ApiResponse;
import com.example.floor_myshop.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coderpwh
 * @since 2021-12-19
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private IShopService shopService;

    @GetMapping("getStoreInfo/{id}")
    public ApiResponse<Shop> getStoreInfo(@PathVariable("id") Integer id){
        Shop res = shopService.getById(id);
        return ApiResponse.success("查询店铺信息成功",res);
    }



}


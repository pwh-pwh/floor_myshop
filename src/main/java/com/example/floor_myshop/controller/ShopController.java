package com.example.floor_myshop.controller;


import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.floor_myshop.entity.ProductOrder;
import com.example.floor_myshop.entity.Shop;
import com.example.floor_myshop.model.ApiResponse;
import com.example.floor_myshop.service.IProductOrderService;
import com.example.floor_myshop.service.IShopService;
import com.example.floor_myshop.util.ControllerUtils;
import com.example.floor_myshop.util.DateTimeUtils;
import com.example.floor_myshop.vo.StoreDashVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

    @Autowired
    private IProductOrderService productOrderService;

    @GetMapping("/getStoreInfo/{id}")
    public ApiResponse getStoreInfo(@PathVariable("id") Integer id){
        final Shop one = shopService.getById(id);
        return ApiResponse.success("查询店铺信息成功",one);
    }

    @PostMapping("/updateStoreInfo")
    public ApiResponse updateStoreInfo(@RequestBody Shop reqShop){
        ControllerUtils.trySetImg(reqShop,reqShop.getShopImg(),(pv, p) -> pv.setShopImg(p));
        if (shopService.updateById(reqShop)){
            final Shop one = shopService.getById(reqShop.getShopId());
            return ApiResponse.success("更新 店铺信息成功",one);
        } else {
            return ApiResponse.failed("更新店铺信息失败");
        }
    }


    @GetMapping("/getStoreDash/{id}")
    public ApiResponse getStoreDash(@PathVariable("id") Integer id){
        final Date yesterdayYesterday = DateTime.now().offset(DateField.DAY_OF_MONTH, -2).toJdkDate();
        final Date yesterday = DateTime.now().offset(DateField.DAY_OF_MONTH, -1).toJdkDate();
        final Date now = DateTime.now().toJdkDate();
        final long yesterdayVisitCount = 1001;
        final long todayVisitCount = 1002;
        final long yesterdayOrderCount = productOrderService.count(Wrappers.<ProductOrder>lambdaQuery()
                        .eq(ProductOrder::getShopId,id)
                .between(ProductOrder::getCreateTime,
                        DateTimeUtils.toLocalDateTimeFromDate(yesterdayYesterday),
                        DateTimeUtils.toLocalDateTimeFromDate(yesterday)
                )
        );
        final long todayOrderCount = productOrderService.count(Wrappers.<ProductOrder>lambdaQuery()
                .eq(ProductOrder::getShopId,id)
                .between(ProductOrder::getCreateTime,
                        DateTimeUtils.toLocalDateTimeFromDate(yesterday),
                        DateTimeUtils.toLocalDateTimeFromDate(now)
                )
        );
        final long waitPayCount = productOrderService.count(Wrappers.<ProductOrder>lambdaQuery()
                .eq(ProductOrder::getShopId,id)
                .eq(ProductOrder::getOrderState,ProductOrder.WAIT_PAY)
        );
        final long waitSendGoodCount = productOrderService.count(Wrappers.<ProductOrder>lambdaQuery()
                .eq(ProductOrder::getShopId,id)
                .eq(ProductOrder::getOrderState,ProductOrder.WAIT_SEND_GOOD)
        );
        final long waitConfirmReceiveCount = productOrderService.count(Wrappers.<ProductOrder>lambdaQuery()
                .eq(ProductOrder::getShopId,id)
                .eq(ProductOrder::getOrderState,ProductOrder.WAIT_CONFIRM_RECEIVE)
        );

        return ApiResponse.success("查询店铺信息成功",new StoreDashVo((int)todayVisitCount,(int)yesterdayVisitCount,
                (int)todayOrderCount,(int)yesterdayOrderCount,
                (int)waitConfirmReceiveCount,(int)waitSendGoodCount,(int)waitPayCount));
    }
}


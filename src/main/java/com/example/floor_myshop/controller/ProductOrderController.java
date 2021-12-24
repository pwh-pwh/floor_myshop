package com.example.floor_myshop.controller;


import com.example.floor_myshop.entity.ProductOrder;
import com.example.floor_myshop.model.ApiResponse;
import com.example.floor_myshop.service.IProductOrderService;
import com.example.floor_myshop.service.IProductService;
import com.example.floor_myshop.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coderpwh & tong
 * @since 2021-12-19
 */
@RestController
@RequestMapping("/productOrder")
public class ProductOrderController {

    @Autowired
    private IProductOrderService productOrderService;
    @Autowired
    private IProductService productService;



//    @GetMapping("/getOrderList")
//    public ApiResponse getOrderList(
//            @RequestBody OrderCondition orderCondition
//            ){
//
//    }

    @PostMapping("/updateOrder")
    public ApiResponse<OrderVo> updateOrder(
            @RequestParam("orderId") Integer orderId,
            @RequestParam(value = "orderState",required = false) Integer orderState,
            @RequestParam(value = "logisticsOrderNumber", required = false) String logisticsOrderNumber
                                            ){
        OrderVo orderVo = new OrderVo();
        orderVo.setOrderId(orderId);
        orderVo.setOrderState(orderState);
        orderVo.setLogisticsOrderNumber(logisticsOrderNumber);
        if (productOrderService.updateById(orderVo.toProductOrder())) {
            final ProductOrder productOrderServiceById = productOrderService.getById(orderVo.getOrderId());
            return ApiResponse.success("更新订单成功",
                    productOrderServiceById.toOrderVo(orderVo.getShopName(),
                            orderVo.getShopImg(),orderVo.getOnePrice(),orderVo.getTotalPrice(),
                            null
                    ));
        } else {
            return ApiResponse.failed("更新订单失败");
        }

    }


}


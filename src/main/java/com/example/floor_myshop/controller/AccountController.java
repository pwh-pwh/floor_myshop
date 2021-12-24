package com.example.floor_myshop.controller;


import com.example.floor_myshop.entity.Account;
import com.example.floor_myshop.entity.Person;
import com.example.floor_myshop.model.ApiResponse;
import com.example.floor_myshop.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.floor_myshop.model.ApiResponse.failed;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coderpwh
 * @since 2021-12-19
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private IAccountService service;

    @PostMapping("/register")
    public ApiResponse register(@RequestBody Account account){
        Person p = service.register(account);
        if (ObjectUtils.isEmpty(p)) {
            return ApiResponse.failed("注册失败",503);
        }
        else{
            return ApiResponse.success("成功注册",p);
        }
    }
    @PostMapping("/login")
    public ApiResponse login(@RequestBody Account account) {
        Account accountDb = service.findByAc(account);
        if (ObjectUtils.isEmpty(accountDb)) return ApiResponse.failed("账号名或者密码错误",503);
        return ApiResponse.success("登录成功",accountDb);
    }



}


package com.example.floor_myshop.controller;


import com.alibaba.druid.util.StringUtils;
import com.example.floor_myshop.entity.Account;
import com.example.floor_myshop.entity.Person;
import com.example.floor_myshop.model.ApiResponse;
import com.example.floor_myshop.model.EmailModel;
import com.example.floor_myshop.service.IAccountService;
import com.example.floor_myshop.service.IPersonService;
import com.example.floor_myshop.util.MailSendUtils;
import com.example.floor_myshop.vo.AccountVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;

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
    @Autowired
    private IPersonService personService;
    private String code;

    @Autowired
    private MailSendUtils mailSendUtils;

    @PostMapping("/code")
    public ApiResponse getCode(@RequestBody Account account) {
        Random random = new Random();
        String temp = "";
        for (int i = 0; i < 5; i++) {
            temp+=String.valueOf(random.nextInt(10));
        }
        code = temp;
        EmailModel emailModel = new EmailModel();
        emailModel.setEmailTheme("校验码");
        emailModel.setRecieverName(account.getUserName());
        emailModel.setEmailContent(code);
        emailModel.setRecieverEmailAddress(account.getUserName());
        mailSendUtils.sendCode(emailModel);
        return ApiResponse.success("已发送code");
    }


    @PostMapping("/register")
    public ApiResponse register(@RequestBody AccountVo account){
        if (!StringUtils.equals(code,account.getCd())){
            return ApiResponse.failed("校验码错误",500);
        }
        Account ac = new Account();
        BeanUtils.copyProperties(account,ac);
        Person p = service.register(ac);
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
        Person byId = personService.getById(accountDb.getAccountId());
        return ApiResponse.success(byId);
    }



}


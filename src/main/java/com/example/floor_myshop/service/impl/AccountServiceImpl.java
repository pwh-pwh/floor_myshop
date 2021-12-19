package com.example.floor_myshop.service.impl;

import com.example.floor_myshop.entity.Account;
import com.example.floor_myshop.mapper.AccountMapper;
import com.example.floor_myshop.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author coderpwh
 * @since 2021-12-19
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

}

package com.example.floor_myshop.controller;


import com.example.floor_myshop.entity.Category;
import com.example.floor_myshop.model.ApiResponse;
import com.example.floor_myshop.service.ICategoryService;
import com.example.floor_myshop.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/getCategoryList")
    public ApiResponse getCategoryList(){
        final List<Category> list = categoryService.list();
        final List<CategoryVo> categoryVoList = Category.toCategoryVoList(list);
        return ApiResponse.success(categoryVoList);
    }

//    public ApiResponse

}


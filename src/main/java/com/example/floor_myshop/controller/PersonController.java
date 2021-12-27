package com.example.floor_myshop.controller;


import com.example.floor_myshop.entity.Person;
import com.example.floor_myshop.model.ApiResponse;
import com.example.floor_myshop.service.IPersonService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coderpwh
 * @since 2021-12-19
 */
@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    IPersonService personService;

    @GetMapping("info")
    public ApiResponse info(@RequestParam Integer id) {
        Person byId = personService.getById(id);
        return ApiResponse.success(byId);
    }
    @PostMapping("update")
    public ApiResponse update(@RequestBody Person p ) {
        personService.updateById(p);
        return ApiResponse.success("update success");
    }


}


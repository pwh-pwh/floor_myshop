package com.example.floor_myshop;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
class FloorMyshopApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void generator() {
        FastAutoGenerator.create("jdbc:mysql://159.75.211.245:3306/floor_shop?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false", "pwh", "123456")
                .globalConfig(builder -> builder.outputDir("/home/pwh/IdeaProjects/floor_myshop/src/main/java").author("coderpwh"))
                .strategyConfig(builder -> builder.addInclude("person","shop","category","product"
                        ,"product_order","head_line","account","diary","science"))
                .packageConfig(builder -> builder.parent("com.example.floor_myshop"))
                .execute();

    }

}

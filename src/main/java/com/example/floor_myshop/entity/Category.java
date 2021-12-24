package com.example.floor_myshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.floor_myshop.vo.CategoryVo;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author coderpwh
 * @since 2021-12-19
 */
@AllArgsConstructor
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;

    private String categoryName;

    private String categoryDesc;

    private Integer priority;

    private LocalDateTime createTime;

    private LocalDateTime lastEditTime;

    private Integer shopId;

    private Integer isDeleted;


    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public  String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(LocalDateTime lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Category{" +
        "categoryId=" + categoryId +
        ", categoryName=" + categoryName +
        ", categoryDesc=" + categoryDesc +
        ", priority=" + priority +
        ", createTime=" + createTime +
        ", lastEditTime=" + lastEditTime +
        ", shopId=" + shopId +
        ", isDeleted=" + isDeleted +
        "}";
    }

    public CategoryVo toCategoryVo(){
        return new CategoryVo(categoryId,categoryName,categoryDesc,priority,createTime,lastEditTime,
                shopId,isDeleted);
    }

    public static List<CategoryVo> toCategoryVoList(List<Category> categoryList){
        final List<CategoryVo> collect = categoryList.stream().map(new Function<Category, CategoryVo>() {
            @Override
            public CategoryVo apply(Category category) {
                return category.toCategoryVo();
            }
        }).collect(Collectors.toList());
        return collect;
    }


}

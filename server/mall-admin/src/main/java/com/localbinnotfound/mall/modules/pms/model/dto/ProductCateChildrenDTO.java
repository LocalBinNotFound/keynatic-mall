package com.localbinnotfound.mall.modules.pms.model.dto;

import com.localbinnotfound.mall.modules.pms.controller.PmsProductCategoryAttributeRelationController;
import com.localbinnotfound.mall.modules.pms.model.PmsProductCategory;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProductCategoryChildrenDTO", description="used for product category children fetching")
public class ProductCateChildrenDTO {
    private Long id;
    private String name;
    private List<PmsProductCategory> children;
}

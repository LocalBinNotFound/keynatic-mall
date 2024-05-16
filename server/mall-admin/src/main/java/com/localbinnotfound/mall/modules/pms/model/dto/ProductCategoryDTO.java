package com.localbinnotfound.mall.modules.pms.model.dto;

import com.localbinnotfound.mall.modules.pms.model.PmsProductCategory;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PmsProductCategoryObjectDTO", description = "used to add or modify product category")
public class ProductCategoryDTO extends PmsProductCategory {
    private List<Long> productAttributeIdList;
}

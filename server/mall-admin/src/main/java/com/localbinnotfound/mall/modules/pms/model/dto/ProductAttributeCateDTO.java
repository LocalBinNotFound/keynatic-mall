package com.localbinnotfound.mall.modules.pms.model.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.localbinnotfound.mall.modules.pms.model.PmsProductAttribute;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProductAttributeCategoryDTO", description="used for product category filter property")
public class ProductAttributeCateDTO {
    private Long id;
    private String name;
    private List<PmsProductAttribute> productAttributeList;
}

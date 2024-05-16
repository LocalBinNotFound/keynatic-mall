package com.localbinnotfound.mall.modules.pms.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ProductUpdateInitDTO", description = "used to initialize product update info")
public class ProductUpdateInitDTO extends ProductSaveParamsDTO{
    private Long cateParentId;

}

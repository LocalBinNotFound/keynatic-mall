package com.localbinnotfound.mall.modules.pms.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PmsProductAttributeInfoDTO", description = "used to retrieve saved attribute info from backend")
public class PmsAttrInfoDTO {
    private Long attributeCategoryId;
    private Long attributeId;
}

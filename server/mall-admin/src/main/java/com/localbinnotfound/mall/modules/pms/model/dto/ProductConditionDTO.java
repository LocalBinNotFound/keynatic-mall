package com.localbinnotfound.mall.modules.pms.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ProductConditionDTO", description="used to manage product filtering")
public class ProductConditionDTO {
    @ApiModelProperty(value="keyword")
    private String keyword;

    @ApiModelProperty(value="page number")
    private Integer pageNum;
    @ApiModelProperty(value="page size")
    private Integer pageSize;
    @ApiModelProperty(value="publishing status 0 -> unpublished; 1 -> published")
    private Integer publishStatus;
    @ApiModelProperty(value="verify status: 0 -> unverified; 1 -> verified")
    private Integer verifyStatus;
    @ApiModelProperty(value="product serial number")
    private String productSn;
    @ApiModelProperty(value="product category id")
    private Long productCategoryId;
    @ApiModelProperty(value="brand id")
    private Long brandId;
}

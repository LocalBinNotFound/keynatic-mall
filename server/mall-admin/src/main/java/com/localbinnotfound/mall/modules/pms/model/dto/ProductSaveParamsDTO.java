package com.localbinnotfound.mall.modules.pms.model.dto;

import com.localbinnotfound.mall.modules.pms.model.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ProductSaveParamsDTO")
public class ProductSaveParamsDTO extends PmsProduct {
    private List<PmsMemberPrice> memberPriceList;
    private List<PmsProductFullReduction> productFullReductionList;
    private List<PmsProductLadder> productLadderList;
    private List<PmsProductAttributeValue> attributeValueList;
    private List<PmsSkuStock> skuStockList;

}

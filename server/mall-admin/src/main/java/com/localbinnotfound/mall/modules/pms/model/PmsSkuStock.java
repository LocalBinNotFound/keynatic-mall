package com.localbinnotfound.mall.modules.pms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * sku的库存
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-16
 */
@Getter
@Setter
@TableName("pms_sku_stock")
@ApiModel(value = "PmsSkuStock", description = "stock")
public class PmsSkuStock implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long productId;

    @ApiModelProperty("SKU code")
    @NotBlank(message = "SKU cannot be empty")
    private String skuCode;

    private BigDecimal price;

    @ApiModelProperty("Stock")
    private Integer stock;

    @ApiModelProperty("low stock")
    private Integer lowStock;

    @ApiModelProperty("sales property 1")
    private String sp1;

    private String sp2;

    private String sp3;

    @ApiModelProperty("display picture")
    private String pic;

    @ApiModelProperty("sales")
    private Integer sale;

    @ApiModelProperty("promotional price")
    private BigDecimal promotionPrice;

    @ApiModelProperty("lock stock")
    private Integer lockStock;


}

package com.localbinnotfound.mall.modules.pms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

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
@ApiModel(value = "PmsSkuStock对象", description = "sku的库存")
public class PmsSkuStock implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long productId;

    @ApiModelProperty("sku编码")
    private String skuCode;

    private BigDecimal price;

    @ApiModelProperty("库存")
    private Integer stock;

    @ApiModelProperty("预警库存")
    private Integer lowStock;

    @ApiModelProperty("销售属性1")
    private String sp1;

    private String sp2;

    private String sp3;

    @ApiModelProperty("展示图片")
    private String pic;

    @ApiModelProperty("销量")
    private Integer sale;

    @ApiModelProperty("单品促销价格")
    private BigDecimal promotionPrice;

    @ApiModelProperty("锁定库存")
    private Integer lockStock;


}

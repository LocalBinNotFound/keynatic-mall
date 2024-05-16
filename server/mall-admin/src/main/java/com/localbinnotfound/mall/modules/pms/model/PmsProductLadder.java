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
 * 产品阶梯价格表(只针对同商品)
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-16
 */
@Getter
@Setter
@TableName("pms_product_ladder")
@ApiModel(value = "PmsProductLadder对象", description = "产品阶梯价格表(只针对同商品)")
public class PmsProductLadder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long productId;

    @ApiModelProperty("满足的商品数量")
    private Integer count;

    @ApiModelProperty("折扣")
    private BigDecimal discount;

    @ApiModelProperty("折后价格")
    private BigDecimal price;


}

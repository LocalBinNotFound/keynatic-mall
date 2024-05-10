package com.localbinnotfound.mall.modules.pms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 产品属性分类表
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-09
 */
@Getter
@Setter
@TableName("pms_product_attribute_category")
@ApiModel(value = "PmsProductAttributeCategory对象", description = "产品属性分类表")
public class PmsProductAttributeCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    @ApiModelProperty("属性数量")
    private Integer attributeCount;

    @ApiModelProperty("参数数量")
    private Integer paramCount;


}

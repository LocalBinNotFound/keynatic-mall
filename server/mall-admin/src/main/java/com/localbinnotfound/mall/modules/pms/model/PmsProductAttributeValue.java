package com.localbinnotfound.mall.modules.pms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 存储产品参数信息的表
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-02
 */
@Getter
@Setter
@TableName("pms_product_attribute_value")
@ApiModel(value = "PmsProductAttributeValue对象", description = "存储产品参数信息的表")
public class PmsProductAttributeValue implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long productId;

    private Long productAttributeId;

    @ApiModelProperty("手动添加规格或参数的值，参数单值，规格有多个时以逗号隔开")
    private String value;


}

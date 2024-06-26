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
 * 产品分类
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-02
 */
@Getter
@Setter
@TableName("pms_product_category")
@ApiModel(value = "PmsProductCategoryObject", description = "产品分类")
public class PmsProductCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("上机分类的编号：0表示一级分类")
    private Long parentId;

    private String name;

    @ApiModelProperty("分类级别：0->1级；1->2级")
    private Integer level;

    private Integer productCount;

    private String productUnit;

    @ApiModelProperty("是否显示在导航栏：0->不显示；1->显示")
    private Integer navStatus;

    @ApiModelProperty("显示状态：0->不显示；1->显示")
    private Integer showStatus;

    private Integer sort;

    @ApiModelProperty("图标")
    private String icon;

    private String keywords;

    @ApiModelProperty("描述")
    private String description;


}

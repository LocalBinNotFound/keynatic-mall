package com.localbinnotfound.mall.modules.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.localbinnotfound.mall.modules.pms.model.PmsProductAttributeCategory;
import com.localbinnotfound.mall.modules.pms.model.dto.ProductAttributeCateDTO;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 Mapper 接口
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-09
 */
public interface PmsProductAttributeCategoryMapper extends BaseMapper<PmsProductAttributeCategory> {

    List<ProductAttributeCateDTO> getListWithAttr();
}

package com.localbinnotfound.mall.modules.pms.mapper;

import com.localbinnotfound.mall.modules.pms.model.PmsProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.localbinnotfound.mall.modules.pms.model.dto.ProductCateChildrenDTO;

import java.util.List;

/**
 * <p>
 * 产品分类 Mapper 接口
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-02
 */
public interface PmsProductCategoryMapper extends BaseMapper<PmsProductCategory> {

    List<ProductCateChildrenDTO> getWithChildren();
}

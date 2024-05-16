package com.localbinnotfound.mall.modules.pms.mapper;

import com.localbinnotfound.mall.modules.pms.model.PmsProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.localbinnotfound.mall.modules.pms.model.dto.ProductUpdateInitDTO;

/**
 * <p>
 * 商品信息 Mapper 接口
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-02
 */
public interface PmsProductMapper extends BaseMapper<PmsProduct> {

    ProductUpdateInitDTO getUpdateInfo(Long id);
}

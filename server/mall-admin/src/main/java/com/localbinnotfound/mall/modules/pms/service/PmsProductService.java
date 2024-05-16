package com.localbinnotfound.mall.modules.pms.service;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.localbinnotfound.mall.modules.pms.model.PmsProduct;
import com.baomidou.mybatisplus.extension.service.IService;
import com.localbinnotfound.mall.modules.pms.model.dto.ProductConditionDTO;
import com.localbinnotfound.mall.modules.pms.model.dto.ProductSaveParamsDTO;
import com.localbinnotfound.mall.modules.pms.model.dto.ProductUpdateInitDTO;

import java.util.List;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-02
 */
public interface PmsProductService extends IService<PmsProduct> {

    Page list(ProductConditionDTO conditionDTO);

    boolean updateStatus(Integer publishStatus, List<Long> ids, SFunction<PmsProduct, ?> getPublishStatus);

    boolean create(ProductSaveParamsDTO productSaveParamsDTO);

    ProductUpdateInitDTO getUpdateInfo(Long id);

    boolean update(ProductSaveParamsDTO productSaveParamsDTO);
}

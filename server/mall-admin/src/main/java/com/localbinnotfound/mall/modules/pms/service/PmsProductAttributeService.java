package com.localbinnotfound.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.localbinnotfound.mall.modules.pms.model.PmsProductAttribute;
import com.localbinnotfound.mall.modules.pms.model.dto.PmsAttrInfoDTO;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务类
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-09
 */
public interface PmsProductAttributeService extends IService<PmsProductAttribute> {

    Page list(Long cid, Integer pageNum, Integer pageSize, Integer type);

    boolean create(PmsProductAttribute productAttribute);

    boolean delete(List<Long> ids);

    List<PmsAttrInfoDTO> getAttrInfoByCid(Long cId);
}

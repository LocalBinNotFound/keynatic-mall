package com.localbinnotfound.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.localbinnotfound.mall.modules.pms.model.PmsProductAttributeCategory;

/**
 * <p>
 * 产品属性分类表 服务类
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-09
 */
public interface PmsProductAttributeCategoryService extends IService<PmsProductAttributeCategory> {

    Page list(Integer pageNum, Integer pageSize);

    boolean add(PmsProductAttributeCategory productAttributeCategory);
}

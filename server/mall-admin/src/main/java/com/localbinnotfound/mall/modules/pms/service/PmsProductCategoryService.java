package com.localbinnotfound.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.localbinnotfound.mall.modules.pms.model.PmsProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-02
 */
public interface PmsProductCategoryService extends IService<PmsProductCategory> {

    /**
     * @param parentId
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page list(Long parentId, Integer pageNum, Integer pageSize);
    boolean updateNavStatus(List<Long> ids, Integer navStatus);

    boolean updateShowStatus(List<Long> ids, Integer showStatus);
}

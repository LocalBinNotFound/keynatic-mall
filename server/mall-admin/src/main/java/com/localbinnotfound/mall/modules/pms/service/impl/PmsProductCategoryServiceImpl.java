package com.localbinnotfound.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.localbinnotfound.mall.modules.pms.model.PmsProductCategory;
import com.localbinnotfound.mall.modules.pms.mapper.PmsProductCategoryMapper;
import com.localbinnotfound.mall.modules.pms.service.PmsProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-02
 */
@Service
public class PmsProductCategoryServiceImpl extends ServiceImpl<PmsProductCategoryMapper, PmsProductCategory> implements PmsProductCategoryService {

    @Override
    public Page list(Long parentId, Integer pageNum, Integer pageSize) {
        Page page = new Page(pageNum, pageSize);

        QueryWrapper<PmsProductCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PmsProductCategory::getParentId, parentId);
        return this.page(page, queryWrapper);
    }
}

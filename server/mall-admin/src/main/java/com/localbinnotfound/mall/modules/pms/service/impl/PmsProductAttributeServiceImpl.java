package com.localbinnotfound.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.localbinnotfound.mall.modules.pms.mapper.PmsProductAttributeMapper;
import com.localbinnotfound.mall.modules.pms.model.PmsProductAttribute;
import com.localbinnotfound.mall.modules.pms.service.PmsProductAttributeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品属性参数表 服务实现类
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-09
 */
@Service
public class PmsProductAttributeServiceImpl extends ServiceImpl<PmsProductAttributeMapper, PmsProductAttribute> implements PmsProductAttributeService {

    @Override
    public Page list(Long cid, Integer pageNum, Integer pageSize, Integer type) {
        Page page = new Page(pageNum, pageSize);

        QueryWrapper<PmsProductAttribute> wrapper = new QueryWrapper<>();

        wrapper.lambda()
                .eq(PmsProductAttribute::getProductAttributeCategoryId, cid)
                .eq(PmsProductAttribute::getType, type);

        return this.page(page, wrapper);
    }
}

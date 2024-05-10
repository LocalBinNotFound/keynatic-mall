package com.localbinnotfound.mall.modules.pms.service.impl;

import com.amazonaws.internal.ConnectionUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.localbinnotfound.mall.modules.pms.mapper.PmsProductAttributeMapper;
import com.localbinnotfound.mall.modules.pms.model.PmsProductAttribute;
import com.localbinnotfound.mall.modules.pms.model.PmsProductAttributeCategory;
import com.localbinnotfound.mall.modules.pms.service.PmsProductAttributeCategoryService;
import com.localbinnotfound.mall.modules.pms.service.PmsProductAttributeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

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

    @Autowired
    PmsProductAttributeMapper productAttributeMapper;

    @Autowired
    PmsProductAttributeCategoryService productAttributeCategoryService;

    @Override
    public Page list(Long cid, Integer pageNum, Integer pageSize, Integer type) {
        Page page = new Page(pageNum, pageSize);

        QueryWrapper<PmsProductAttribute> wrapper = new QueryWrapper<>();

        wrapper.lambda()
                .eq(PmsProductAttribute::getProductAttributeCategoryId, cid)
                .eq(PmsProductAttribute::getType, type);

        return this.page(page, wrapper);
    }

    @Override
    @Transactional
    public boolean create(PmsProductAttribute productAttribute) {
        boolean save = this.save(productAttribute);

        UpdateWrapper<PmsProductAttributeCategory> updateWrapper = new UpdateWrapper<>();
        if (save) {
            if (productAttribute.getType() == 0) updateWrapper.setSql("attribute_count = attribute_count + 1");
            else if(productAttribute.getType() == 1) updateWrapper.setSql("param_count = param_count+1");
            updateWrapper.lambda().eq(PmsProductAttributeCategory::getId, productAttribute.getProductAttributeCategoryId());
            productAttributeCategoryService.update(updateWrapper);
        }
        return save;
    }

    @Override
    @Transactional
    public boolean delete(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) return false;
        PmsProductAttribute productAttribute = null;

        for (Long id : ids) {
            productAttribute = this.getById(id);
            if (productAttribute != null) break;
        }

        int length = productAttributeMapper.deleteBatchIds(ids);

        if (length > 0 && productAttribute != null) {
            UpdateWrapper<PmsProductAttributeCategory> updateWrapper = new UpdateWrapper<>();
            if (productAttribute.getType() == 0) updateWrapper.setSql("attribute_count = attribute_count-"+length);
            else if (productAttribute.getType() == 1) updateWrapper.setSql("param_count = param_count-"+length);
            updateWrapper.lambda().eq(PmsProductAttributeCategory::getId, productAttribute.getProductAttributeCategoryId());
            productAttributeCategoryService.update(updateWrapper);
        }
        return length > 0;
    }
}

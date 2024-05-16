package com.localbinnotfound.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.localbinnotfound.mall.modules.pms.mapper.PmsProductAttributeCategoryMapper;
import com.localbinnotfound.mall.modules.pms.model.PmsProductAttributeCategory;
import com.localbinnotfound.mall.modules.pms.model.dto.ProductAttributeCateDTO;
import com.localbinnotfound.mall.modules.pms.service.PmsProductAttributeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 服务实现类
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-09
 */
@Service
public class PmsProductAttributeCategoryServiceImpl extends ServiceImpl<PmsProductAttributeCategoryMapper, PmsProductAttributeCategory> implements PmsProductAttributeCategoryService {

    @Autowired
    PmsProductAttributeCategoryMapper productAttributeCategoryMapper;

    @Override
    public Page list(Integer pageNum, Integer pageSize) {
        Page page = new Page(pageNum, pageSize);
        return this.page(page);
    }

    @Override
    public boolean add(PmsProductAttributeCategory productAttributeCategory) {
        productAttributeCategory.setAttributeCount(0);
        productAttributeCategory.setParamCount(0);
        return this.save(productAttributeCategory);
    }

    @Override
    public List<ProductAttributeCateDTO> getListWithAttr() {
        return productAttributeCategoryMapper.getListWithAttr();
    }
}

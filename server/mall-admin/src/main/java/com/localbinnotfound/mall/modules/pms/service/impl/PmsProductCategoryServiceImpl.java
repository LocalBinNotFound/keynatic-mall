package com.localbinnotfound.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.localbinnotfound.mall.modules.pms.model.PmsProductCategory;
import com.localbinnotfound.mall.modules.pms.mapper.PmsProductCategoryMapper;
import com.localbinnotfound.mall.modules.pms.model.PmsProductCategoryAttributeRelation;
import com.localbinnotfound.mall.modules.pms.model.dto.PmsProductCategoryDTO;
import com.localbinnotfound.mall.modules.pms.service.PmsProductCategoryAttributeRelationService;
import com.localbinnotfound.mall.modules.pms.service.PmsProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    PmsProductCategoryAttributeRelationService relationService;

    @Override
    public Page list(Long parentId, Integer pageNum, Integer pageSize) {
        Page page = new Page(pageNum, pageSize);

        QueryWrapper<PmsProductCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PmsProductCategory::getParentId, parentId);
        return this.page(page, queryWrapper);
    }

    @Override
    public boolean updateNavStatus(List<Long> ids, Integer navStatus) {

        UpdateWrapper<PmsProductCategory> pmsProductCategoryUpdateWrapper = new UpdateWrapper<>();
        pmsProductCategoryUpdateWrapper.lambda()
                // cols need update
                .set(PmsProductCategory::getNavStatus,navStatus)
                // condition
                .in(PmsProductCategory::getId, ids);
        return this.update(pmsProductCategoryUpdateWrapper);
    }

    @Override
    public boolean updateShowStatus(List<Long> ids, Integer showStatus) {
        UpdateWrapper<PmsProductCategory> pmsProductCategoryUpdateWrapper = new UpdateWrapper<>();
        pmsProductCategoryUpdateWrapper.lambda()
                .set(PmsProductCategory::getShowStatus, showStatus)
                .in(PmsProductCategory::getId, ids);
        return this.update(pmsProductCategoryUpdateWrapper);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean customSave(PmsProductCategoryDTO productCategoryDTO) {
        boolean isSavedI, isSavedII;

        PmsProductCategory productCategory = new PmsProductCategory();
        // copy DTO data to productCategory
        // need to use this.save to save PmsProductCategory, which has mapper for @TableName
        BeanUtils.copyProperties(productCategoryDTO, productCategory);
        productCategory.setProductCount(0);
        if (productCategory.getParentId() == 0) productCategory.setLevel(0);
        else productCategory.setLevel((int) (productCategory.getParentId()+1));

        isSavedI = this.save(productCategory);

        List<Long> productAttributeIdList = productCategoryDTO.getProductAttributeIdList();
        List<PmsProductCategoryAttributeRelation> list = new ArrayList<>();
        for (Long attrId : productAttributeIdList) {
            PmsProductCategoryAttributeRelation productCategoryAttributeRelation = new PmsProductCategoryAttributeRelation();
            productCategoryAttributeRelation.setProductCategoryId(productCategory.getId());
            productCategoryAttributeRelation.setProductAttributeId(attrId);
            list.add(productCategoryAttributeRelation);
        }

        isSavedII = relationService.saveBatch(list);
        return isSavedI && isSavedII;
    }


}

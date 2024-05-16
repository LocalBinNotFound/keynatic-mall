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
    @Transactional
    public boolean saveAttrInfo(PmsProductCategoryDTO productCategoryDTO) {
        boolean isSavedI, isSavedII;

        PmsProductCategory productCategory = new PmsProductCategory();
        // copy DTO data to productCategory
        // need to use this.save to save PmsProductCategory, which has mapper for @TableName
        BeanUtils.copyProperties(productCategoryDTO, productCategory);
        productCategory.setProductCount(0);
        if (productCategory.getParentId() == 0) productCategory.setLevel(0);
        else productCategory.setLevel(1);

        isSavedI = this.save(productCategory);

        isSavedII = isSavedBatch(productCategoryDTO, productCategory);

        return isSavedI && isSavedII;
    }

    @Override
    public boolean update(PmsProductCategoryDTO productCategoryDTO) {
        PmsProductCategory productCategory = new PmsProductCategory();
        BeanUtils.copyProperties(productCategoryDTO, productCategory);

        if (productCategory.getParentId() == 0) productCategory.setLevel(0);
        else productCategory.setLevel(1);

        this.updateById(productCategory);

        QueryWrapper<PmsProductCategoryAttributeRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PmsProductCategoryAttributeRelation::getProductCategoryId, productCategory.getId());
        relationService.remove(queryWrapper);

        isSavedBatch(productCategoryDTO, productCategory);
        return true;
    }

    private boolean isSavedBatch(PmsProductCategoryDTO productCategoryDTO, PmsProductCategory productCategory) {
        List<PmsProductCategoryAttributeRelation> list = new ArrayList<>();
        for (Long attrId : productCategoryDTO.getProductAttributeIdList()) {
            PmsProductCategoryAttributeRelation productCategoryAttributeRelation = new PmsProductCategoryAttributeRelation();
            productCategoryAttributeRelation.setProductCategoryId(productCategory.getId());
            productCategoryAttributeRelation.setProductAttributeId(attrId);
            list.add(productCategoryAttributeRelation);
        }

        return relationService.saveBatch(list);
    }


}

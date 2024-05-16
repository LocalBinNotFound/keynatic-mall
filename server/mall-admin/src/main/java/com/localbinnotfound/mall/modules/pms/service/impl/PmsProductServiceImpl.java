package com.localbinnotfound.mall.modules.pms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.localbinnotfound.mall.modules.pms.model.PmsProduct;
import com.localbinnotfound.mall.modules.pms.mapper.PmsProductMapper;
import com.localbinnotfound.mall.modules.pms.model.dto.ProductConditionDTO;
import com.localbinnotfound.mall.modules.pms.service.PmsProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-02
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements PmsProductService {

    @Override
    public Page list(ProductConditionDTO conditionDTO) {
        Page page = new Page(conditionDTO.getPageNum(), conditionDTO.getPageSize());

        QueryWrapper<PmsProduct> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<PmsProduct> lambdaWrapper = queryWrapper.lambda();
        if (!StrUtil.isBlank(conditionDTO.getKeyword())) {
            lambdaWrapper.like(PmsProduct::getName, conditionDTO.getKeyword());
        }

        if (!StrUtil.isBlank(conditionDTO.getProductSn())) {
            lambdaWrapper.like(PmsProduct::getProductSn, conditionDTO.getProductSn());
        }

        if (conditionDTO.getProductCategoryId()!=null && conditionDTO.getProductCategoryId()>0) {
            lambdaWrapper.like(PmsProduct::getProductCategoryId, conditionDTO.getProductCategoryId());
        }

        if (conditionDTO.getBrandId()!= null && conditionDTO.getBrandId()>0) {
            lambdaWrapper.like(PmsProduct::getBrandId, conditionDTO.getBrandId());
        }

        if (conditionDTO.getPublishStatus()!= null && conditionDTO.getPublishStatus()>0) {
            lambdaWrapper.like(PmsProduct::getPublishStatus, conditionDTO.getPublishStatus());
        }

        if (conditionDTO.getVerifyStatus()!= null && conditionDTO.getVerifyStatus()>0) {
            lambdaWrapper.like(PmsProduct::getVerifyStatus, conditionDTO.getVerifyStatus());
        }

        return this.page(page, lambdaWrapper);
    }

    @Override
    public boolean updateStatus(Integer status, List<Long> ids, SFunction<PmsProduct, ?> getStatus) {
        UpdateWrapper<PmsProduct> updateWrapper = new UpdateWrapper<>();

        updateWrapper.lambda().set(getStatus, status)
                .in(PmsProduct::getId, ids);

        return this.update(updateWrapper);
    }
}

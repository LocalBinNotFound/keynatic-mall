package com.localbinnotfound.mall.modules.pms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.localbinnotfound.mall.modules.pms.model.PmsMemberPrice;
import com.localbinnotfound.mall.modules.pms.model.PmsProduct;
import com.localbinnotfound.mall.modules.pms.mapper.PmsProductMapper;
import com.localbinnotfound.mall.modules.pms.model.PmsProductAttributeValue;
import com.localbinnotfound.mall.modules.pms.model.dto.ProductConditionDTO;
import com.localbinnotfound.mall.modules.pms.model.dto.ProductSaveParamsDTO;
import com.localbinnotfound.mall.modules.pms.model.dto.ProductUpdateInitDTO;
import com.localbinnotfound.mall.modules.pms.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
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

    @Autowired
    PmsProductMapper productMapper;

    @Autowired
    PmsMemberPriceService memberPriceService;

    @Autowired
    PmsProductLadderService productLadderService;

    @Autowired
    PmsProductFullReductionService fullReductionService;

    @Autowired
    PmsSkuStockService skuStockService;

    @Autowired
    PmsProductAttributeValueService attributeValueService;

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

        lambdaWrapper.orderByAsc(PmsProduct::getSort);
        return this.page(page, lambdaWrapper);
    }

    @Override
    public boolean updateStatus(Integer status, List<Long> ids, SFunction<PmsProduct, ?> getStatus) {
        UpdateWrapper<PmsProduct> updateWrapper = new UpdateWrapper<>();

        updateWrapper.lambda().set(getStatus, status)
                .in(PmsProduct::getId, ids);

        return this.update(updateWrapper);
    }

    @Override
    @Transactional
    public boolean create(ProductSaveParamsDTO productSaveParamsDTO) {
        PmsProduct product = productSaveParamsDTO;
        product.setId(null);
        boolean result = this.save(product);

        if (result) {

            switch (product.getPromotionType()) {
                case 2:
                    SaveList(productSaveParamsDTO.getMemberPriceList(), product.getId(), memberPriceService);
                    break;
                case 3:
                    SaveList(productSaveParamsDTO.getProductLadderList(), product.getId(), productLadderService);
                    break;
                case 4:
                    SaveList(productSaveParamsDTO.getProductFullReductionList(), product.getId(), fullReductionService);
                    break;
            }
            SaveList(productSaveParamsDTO.getAttributeValueList(), product.getId(), attributeValueService);
            SaveList(productSaveParamsDTO.getSkuStockList(), product.getId(), skuStockService);
        }

        return result;
    }

    @Override
    public ProductUpdateInitDTO getUpdateInfo(Long id) {
        return productMapper.getUpdateInfo(id);
    }

    @Override
    public boolean update(ProductSaveParamsDTO productSaveParamsDTO) {
        PmsProduct product = productSaveParamsDTO;
        boolean result = this.updateById(product);

        if (result) {

            switch (product.getPromotionType()) {
                case 2:
                    DeleteListByProductId(product.getId(), memberPriceService);
                    SaveList(productSaveParamsDTO.getMemberPriceList(), product.getId(), memberPriceService);
                    break;
                case 3:
                    DeleteListByProductId(product.getId(), productLadderService);
                    SaveList(productSaveParamsDTO.getProductLadderList(), product.getId(), productLadderService);
                    break;
                case 4:
                    DeleteListByProductId(product.getId(), fullReductionService);
                    SaveList(productSaveParamsDTO.getProductFullReductionList(), product.getId(), fullReductionService);
                    break;
            }
            DeleteListByProductId(product.getId(), attributeValueService);
            SaveList(productSaveParamsDTO.getAttributeValueList(), product.getId(), attributeValueService);

            DeleteListByProductId(product.getId(), skuStockService);
            SaveList(productSaveParamsDTO.getSkuStockList(), product.getId(), skuStockService);
        }

        return result;
    }

    public void SaveList(List list, Long productId, IService service) {
        if (CollectionUtils.isEmpty(list)) return;

        try {
            for (Object obj : list) {
                Method setProductIdMethod = obj.getClass().getMethod("setProductId", Long.class);

                Method setId = obj.getClass().getMethod("setId", Long.class);
                setId.invoke(obj, (Long) null);
                setProductIdMethod.invoke(obj, productId);
            }
            service.saveBatch(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void DeleteListByProductId(Long productId, IService service) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("product_id", productId);

        service.remove(queryWrapper);
    }
}

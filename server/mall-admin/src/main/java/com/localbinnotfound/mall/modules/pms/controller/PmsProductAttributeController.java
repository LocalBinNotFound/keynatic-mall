package com.localbinnotfound.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.localbinnotfound.mall.common.api.CommonPage;
import com.localbinnotfound.mall.common.api.CommonResult;
import com.localbinnotfound.mall.modules.pms.model.*;
import com.localbinnotfound.mall.modules.pms.model.dto.AttrInfoDTO;
import com.localbinnotfound.mall.modules.pms.service.PmsProductAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 前端控制器
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-09
 */
@RestController
@RequestMapping("/productAttribute")
public class PmsProductAttributeController {

    @Autowired
    PmsProductAttributeService productAttributeService;

    @RequestMapping(value="/list/{cid}", method = RequestMethod.GET)
    public CommonResult getList(@PathVariable Long cid,
                                @RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
                                @RequestParam(value="pageSize", defaultValue="5") Integer pageSize,
                                @RequestParam(value="type") Integer type) {
        Page page = productAttributeService.list(cid, pageNum, pageSize, type);

        return CommonResult.success(CommonPage.restPage(page));
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody PmsProductAttribute productAttribute) {
        boolean result = productAttributeService.create(productAttribute);
        if (result) return CommonResult.success(true);
        return CommonResult.failed();
    }

    @RequestMapping(value="update/{id}", method = RequestMethod.POST)
    public CommonResult update(@RequestBody PmsProductAttribute productAttribute) {
        boolean result = productAttributeService.updateById(productAttribute);
        if (result) return CommonResult.success(true);
        else return CommonResult.failed();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public CommonResult<PmsProductAttribute> getById(@PathVariable Long id) {
        PmsProductAttribute productAttribute = productAttributeService.getById(id);
        return CommonResult.success(productAttribute);
    }

    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        boolean result = productAttributeService.delete(ids);
        if (result) return CommonResult.success(true);
        else return CommonResult.failed();
    }

    @RequestMapping(value="/attrInfo/{cId}", method = RequestMethod.GET)
    public CommonResult getAttrInfoByCid(@PathVariable Long cId) {
        List<AttrInfoDTO> list = productAttributeService.getAttrInfoByCid(cId);
        return CommonResult.success(list);
    }
}

